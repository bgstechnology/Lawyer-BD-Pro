package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.api.Content
import com.example.api.GenerateContentRequest
import com.example.api.Part
import com.example.api.RetrofitClient
import com.example.BuildConfig
import com.example.data.CaseRepository
import com.example.data.LegalCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(private val repository: CaseRepository) : ViewModel() {
    
    val allCases: StateFlow<List<LegalCase>> = repository.allCases
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertCase(legalCase: LegalCase) {
        viewModelScope.launch {
            repository.insert(legalCase)
        }
    }

    fun deleteCase(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }

    // AI Assistant State
    private val _aiResponses = MutableStateFlow<List<Pair<String, Boolean>>>(emptyList())
    val aiResponses = _aiResponses.asStateFlow()

    private val _isLoadingAi = MutableStateFlow(false)
    val isLoadingAi = _isLoadingAi.asStateFlow()

    fun askAiAssistant(query: String) {
        val currentList = _aiResponses.value.toMutableList()
        currentList.add(Pair(query, true)) // true for User message
        _aiResponses.value = currentList

        viewModelScope.launch {
            _isLoadingAi.value = true
            try {
                val apiKey = BuildConfig.GEMINI_API_KEY
                val request = GenerateContentRequest(
                    contents = listOf(
                        Content(parts = listOf(Part(text = query)))
                    ),
                    systemInstruction = Content(
                        parts = listOf(Part(text = "You are a professional AI Legal Assistant for lawyers in Bangladesh. Provide accurate legal references (e.g. Bangladesh Penal Code, CrPC). Explain clearly. Always add a disclaimer that you are an AI assistant and not a substitute for professional legal advice."))
                    )
                )
                val response = RetrofitClient.service.generateContent(apiKey, request)
                val replyText = response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No response received."
                
                val updatedList = _aiResponses.value.toMutableList()
                updatedList.add(Pair(replyText, false)) // false for AI response
                _aiResponses.value = updatedList
                
            } catch (e: Exception) {
                val updatedList = _aiResponses.value.toMutableList()
                updatedList.add(Pair("Error: ${e.localizedMessage}", false))
                _aiResponses.value = updatedList
            } finally {
                _isLoadingAi.value = false
            }
        }
    }
}

class AppViewModelFactory(private val repository: CaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
