package ir.hamedan.shopmanagement.domain.usecase.customer

import ir.hamedan.shopmanagement.domain.model.Customer
import ir.hamedan.shopmanagement.domain.repository.CustomerRepository
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer) = repository.deleteCustomer(customer)
}