package ir.hamedan.shopmanagement.domain.usecase.sale

import ir.hamedan.shopmanagement.core.utils.AppException
import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.domain.repository.CustomerRepository
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import javax.inject.Inject

/**
 * ثبت یک فروش شامل سه گام است که این UseCase هماهنگشون می‌کند:
 * ۱. اعتبارسنجی و بررسی کافی بودن موجودی محصول
 * ۲. ثبت رکورد فروش
 * ۳. کسر موجودی از انبار و در صورت نسیه بودن، افزایش بدهی مشتری
 *
 * نکته: در نسخه‌ی production پیشنهاد می‌شود این عملیات داخل یک Room @Transaction
 * (مثلاً از طریق AppDatabase.withTransaction {}) اجرا شود تا atomic باشد.
 */
class CreateSaleUseCase @Inject constructor(
    private val saleRepository: SaleRepository,
    private val productRepository: ProductRepository,
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(sale: Sale): Long {
        if (sale.quantity <= 0) {
            throw AppException.ValidationException("تعداد", "باید بزرگ‌تر از صفر باشد")
        }
        if (sale.unitPrice < 0 || sale.paidAmount < 0) {
            throw AppException.ValidationException("مبلغ", "نمی‌تواند منفی باشد")
        }

        val product = productRepository.getProductById(sale.productId)
            ?: throw AppException.NotFoundException("محصول", sale.productId)

        if (product.quantity < sale.quantity) {
            throw AppException.ValidationException(
                "موجودی",
                "موجودی کافی نیست (موجودی فعلی: ${product.quantity})"
            )
        }

        val saleId = saleRepository.addSale(sale)
        productRepository.adjustQuantity(sale.productId, -sale.quantity)

        if (sale.remainingAmount > 0.0 && sale.customerId != null) {
            customerRepository.adjustDebt(sale.customerId, sale.remainingAmount)
        }

        return saleId
    }
}