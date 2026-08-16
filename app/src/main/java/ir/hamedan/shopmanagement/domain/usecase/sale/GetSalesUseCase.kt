package ir.hamedan.shopmanagement.domain.usecase.sale

import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSalesUseCase @Inject constructor(
    private val repository: SaleRepository
) {
    /** بدون پارامتر همه‌ی فروش‌ها، با dateRange فقط بازه‌ی مشخص، با customerId فقط یک مشتری */
    operator fun invoke(
        startDate: Long? = null,
        endDate: Long? = null,
        customerId: Long? = null
    ): Flow<List<Sale>> = when {
        customerId != null -> repository.getSalesByCustomer(customerId)
        startDate != null && endDate != null -> repository.getSalesByDateRange(startDate, endDate)
        else -> repository.getAllSales()
    }
}