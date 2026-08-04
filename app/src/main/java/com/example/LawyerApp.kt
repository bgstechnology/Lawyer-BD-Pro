package com.example

import android.app.Application
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.CaseRepository

class LawyerApp : Application() {
    val database by lazy { Room.databaseBuilder(this, AppDatabase::class.java, "lawyer_db").build() }
    val repository by lazy { CaseRepository(database.caseDao()) }
}
