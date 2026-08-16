package ir.hamedan.shopmanagement.domain.usecase.purchase

import ir.hamedan.shopmanagement.core.util.AppException
import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import javax.inject.Inject

/**
 * ثبت خرید از تامین‌کننده: رکورد خرید ذخیره و موجودی محصول افزایش پیدا می‌کند.
 * مدیریت بدهی به تامین‌کننده (SupplierRepository.adjustDebt) در نسخه‌ی بعدی
 * که ماژول Supplier کامل شود اضافه خواهد شد.
 */
class CreatePurchaseUseCase @Inject constructor(
    private val purchaseRepository: PurchaseRepository,
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(purchase: Purchase): Long {
        if (purchase.quantity <= 0) {
            throw AppException.ValidationException("تعداد", "باید بزرگ‌تر از صفر باشد")
        }
        if (purchase.unitCost < 0 || purchase.paidAmount < 0) {
            throw AppException.ValidationException("مبلغ", "نمی‌تواند منفی باشد")
        }

        productRepository.getProductById(purchase.productId)
            ?: throw AppException.NotFoundException("محصول", purchase.productId)

        val purchaseId = purchaseRepository.addPurchase(purchase)
        productRepository.adjustQuantity(purchase.productId, purchase.quantity)

        return purchaseId
    }
}