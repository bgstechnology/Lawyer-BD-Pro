package com.example.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cases")
data class LegalCase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caseNumber: String,
    val courtName: String,
    val clientName: String,
    val hearingDate: String,
    val status: String,
    val category: String,
    val notes: String
)

@Dao
interface CaseDao {
    @Query("SELECT * FROM cases ORDER BY id DESC")
    fun getAllCases(): Flow<List<LegalCase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCase(legalCase: LegalCase)

    @Query("DELETE FROM cases WHERE id = :id")
    suspend fun deleteCaseById(id: Int)
}

@Database(entities = [LegalCase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun caseDao(): CaseDao
}

class CaseRepository(private val caseDao: CaseDao) {
    val allCases: Flow<List<LegalCase>> = caseDao.getAllCases()
    suspend fun insert(legalCase: LegalCase) = caseDao.insertCase(legalCase)
    suspend fun deleteById(id: Int) = caseDao.deleteCaseById(id)
}
