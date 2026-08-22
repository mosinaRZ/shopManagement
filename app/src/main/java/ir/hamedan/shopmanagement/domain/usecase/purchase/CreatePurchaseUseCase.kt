package ir.hamedan.shopmanagement.domain.usecase.purchase

import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import javax.inject.Inject

class CreatePurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository
) {
    suspend operator fun invoke(purchase: Purchase): Long {
        require(purchase.quantity > 0) { "تعداد خرید باید بیشتر از صفر باشد" }
        require(purchase.unitCost >= 0) { "قیمت خرید نامعتبر است" }
        return purchaseRepository.addPurchase(purchase)
    }
}