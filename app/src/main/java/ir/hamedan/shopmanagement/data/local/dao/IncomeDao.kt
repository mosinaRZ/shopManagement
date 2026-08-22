package ir.hamedan.shopmanagement.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.hamedan.shopmanagement.data.local.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Query("SELECT * FROM incomes ORDER BY date DESC")
    fun getAll(): Flow<List<IncomeEntity>>

    @Query("SELECT * FROM incomes WHERE date BETWEEN :start AND :end ORDER BY date DESC")
    fun getByDateRange(start: Long, end: Long): Flow<List<IncomeEntity>>

    @Insert
    suspend fun insert(income: IncomeEntity): Long

    @Delete
    suspend fun delete(income: IncomeEntity)

    @Query("SELECT COALESCE(SUM(amount), 0.0) FROM incomes WHERE date BETWEEN :start AND :end")
    suspend fun getTotalIncome(start: Long, end: Long): Double
}