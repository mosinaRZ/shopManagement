package ir.hamedan.shopmanagement.domain.usecase.purchase

import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPurchasesUseCase @Inject constructor(
    private val repository: PurchaseRepository
) {
    operator fun invoke(
        startDate: Long? = null,
        endDate: Long? = null,
        supplierId: Long? = null
    ): Flow<List<Purchase>> = when {
        supplierId != null -> repository.getPurchasesBySupplier(supplierId)
        startDate != null && endDate != null -> repository.getPurchasesByDateRange(startDate, endDate)
        else -> repository.getAllPurchases()
    }
}