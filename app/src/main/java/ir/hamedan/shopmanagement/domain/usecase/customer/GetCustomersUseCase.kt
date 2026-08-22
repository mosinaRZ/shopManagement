package ir.hamedan.shopmanagement.domain.usecase.customer

import ir.hamedan.shopmanagement.domain.model.Customer
import ir.hamedan.shopmanagement.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> = repository.getAllCustomers()
}