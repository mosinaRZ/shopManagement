package ir.hamedan.shopmanagement.domain.usecase.sale

import ir.hamedan.shopmanagement.core.utils.ValidationUtils
import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import javax.inject.Inject

class CreateSaleUseCase @Inject constructor(
    private val saleRepository: SaleRepository
) {
    suspend operator fun invoke(sale: Sale): Result<Long> {
        if (sale.quantity <= 0) {
            return Result.failure(IllegalArgumentException("تعداد فروش باید بیشتر از صفر باشد"))
        }
        if (!ValidationUtils.isValidPrice(sale.unitPrice)) {
            return Result.failure(IllegalArgumentException("قیمت کالا نامعتبر است"))
        }

        return try {
            // فقط متد addSale صدا زده می‌شود (کسر انبار و ثبت بدهی درون ریپازیتوری انجام می‌گیرد)
            val saleId = saleRepository.addSale(sale)
            Result.success(saleId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}