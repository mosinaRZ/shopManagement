package ir.hamedan.shopmanagement.domain.usecase.purchase

import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPurchasesUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    operator fun invoke(): Flow<List<Purchase>> = purchaseRepository.getAllPurchases()

    fun byDateRange(start: Long, end: Long): Flow<List<Purchase>> =
        purchaseRepository.getPurchasesByDateRange(start, end)
}