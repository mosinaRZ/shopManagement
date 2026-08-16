package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.SaleDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toDomainList
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SaleRepositoryImpl @Inject constructor(
    private val dao: SaleDao
) : SaleRepository {

    override fun getAllSales(): Flow<List<Sale>> =
        dao.getAll().map { it.toDomainList() }

    override fun getSalesByDateRange(start: Long, end: Long): Flow<List<Sale>> =
        dao.getByDateRange(start, end).map { it.toDomainList() }

    override fun getSalesByCustomer(customerId: Long): Flow<List<Sale>> =
        dao.getByCustomer(customerId).map { it.toDomainList() }

    override suspend fun getSaleById(id: Long): Sale? =
        dao.getById(id)?.toDomain()

    override suspend fun addSale(sale: Sale): Long =
        dao.insert(sale.toEntity())

    override suspend fun deleteSale(sale: Sale) {
        dao.delete(sale.toEntity())
    }

    override suspend fun getTotalSalesAmount(start: Long, end: Long): Double =
        dao.getTotalSalesAmount(start, end)
}