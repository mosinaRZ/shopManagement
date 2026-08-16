package ir.hamedan.shopmanagement.domain.repository

import ir.hamedan.shopmanagement.domain.model.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {

    fun getAllPurchases(): Flow<List<Purchase>>

    fun getPurchasesByDateRange(start: Long, end: Long): Flow<List<Purchase>>

    fun getPurchasesBySupplier(supplierId: Long): Flow<List<Purchase>>

    suspend fun getPurchaseById(id: Long): Purchase?

    suspend fun addPurchase(purchase: Purchase): Long

    suspend fun deletePurchase(purchase: Purchase)

    suspend fun getTotalPurchaseAmount(start: Long, end: Long): Double
}