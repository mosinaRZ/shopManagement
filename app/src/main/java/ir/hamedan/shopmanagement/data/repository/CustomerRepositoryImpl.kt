package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.CustomerDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toDomainList
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Customer
import ir.hamedan.shopmanagement.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val dao: CustomerDao
) : CustomerRepository {

    override fun getAllCustomers(): Flow<List<Customer>> =
        dao.getAll().map { it.toDomainList() }

    override suspend fun getCustomerById(id: Long): Customer? =
        dao.getById(id)?.toDomain()

    override suspend fun addCustomer(customer: Customer): Long =
        dao.insert(customer.toEntity())

    override suspend fun updateCustomer(customer: Customer) {
        dao.update(customer.toEntity())
    }

    override suspend fun deleteCustomer(customer: Customer) {
        dao.delete(customer.toEntity())
    }

    override suspend fun adjustDebt(customerId: Long, delta: Double) {
        dao.adjustDebt(customerId, delta)
    }
}