package ir.hamedan.shopmanagement.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.hamedan.shopmanagement.data.local.entity.PurchaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {

    @Query("SELECT * FROM purchases ORDER BY purchaseDate DESC")
    fun getAll(): Flow<List<PurchaseEntity>>

    @Query("SELECT * FROM purchases WHERE purchaseDate BETWEEN :start AND :end ORDER BY purchaseDate DESC")
    fun getByDateRange(start: Long, end: Long): Flow<List<PurchaseEntity>>

    @Query("SELECT * FROM purchases WHERE supplierId = :supplierId ORDER BY purchaseDate DESC")
    fun getBySupplier(supplierId: Long): Flow<List<PurchaseEntity>>

    @Query("SELECT * FROM purchases WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): PurchaseEntity?

    @Insert
    suspend fun insert(purchase: PurchaseEntity): Long

    @Delete
    suspend fun delete(purchase: PurchaseEntity)

    @Query("SELECT COALESCE(SUM(quantity * unitCost), 0.0) FROM purchases WHERE purchaseDate BETWEEN :start AND :end")
    suspend fun getTotalPurchaseAmount(start: Long, end: Long): Double
}