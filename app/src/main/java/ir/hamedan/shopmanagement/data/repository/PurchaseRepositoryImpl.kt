package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.PurchaseDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toDomainList
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(
    private val dao: PurchaseDao
) : PurchaseRepository {

    override fun getAllPurchases(): Flow<List<Purchase>> =
        dao.getAll().map { it.toDomainList() }

    override fun getPurchasesByDateRange(start: Long, end: Long): Flow<List<Purchase>> =
        dao.getByDateRange(start, end).map { it.toDomainList() }

    override fun getPurchasesBySupplier(supplierId: Long): Flow<List<Purchase>> =
        dao.getBySupplier(supplierId).map { it.toDomainList() }

    override suspend fun getPurchaseById(id: Long): Purchase? =
        dao.getById(id)?.toDomain()

    override suspend fun addPurchase(purchase: Purchase): Long =
        dao.insert(purchase.toEntity())

    override suspend fun deletePurchase(purchase: Purchase) {
        dao.delete(purchase.toEntity())
    }

    override suspend fun getTotalPurchaseAmount(start: Long, end: Long): Double =
        dao.getTotalPurchaseAmount(start, end)
}