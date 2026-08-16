package ir.hamedan.shopmanagement.domain.repository

import ir.hamedan.shopmanagement.domain.model.Sale
import kotlinx.coroutines.flow.Flow

interface SaleRepository {

    fun getAllSales(): Flow<List<Sale>>

    fun getSalesByDateRange(start: Long, end: Long): Flow<List<Sale>>

    fun getSalesByCustomer(customerId: Long): Flow<List<Sale>>

    suspend fun getSaleById(id: Long): Sale?

    suspend fun addSale(sale: Sale): Long

    suspend fun deleteSale(sale: Sale)

    suspend fun getTotalSalesAmount(start: Long, end: Long): Double
}