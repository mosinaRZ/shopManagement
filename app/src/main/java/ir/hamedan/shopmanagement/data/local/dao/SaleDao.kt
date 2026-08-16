package ir.hamedan.shopmanagement.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.hamedan.shopmanagement.data.local.entity.SaleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SaleDao {

    @Query("SELECT * FROM sales ORDER BY saleDate DESC")
    fun getAll(): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE saleDate BETWEEN :start AND :end ORDER BY saleDate DESC")
    fun getByDateRange(start: Long, end: Long): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE customerId = :customerId ORDER BY saleDate DESC")
    fun getByCustomer(customerId: Long): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): SaleEntity?

    @Insert
    suspend fun insert(sale: SaleEntity): Long

    @Delete
    suspend fun delete(sale: SaleEntity)

    @Query("SELECT COALESCE(SUM((quantity * unitPrice) - discount), 0.0) FROM sales WHERE saleDate BETWEEN :start AND :end")
    suspend fun getTotalSalesAmount(start: Long, end: Long): Double
}