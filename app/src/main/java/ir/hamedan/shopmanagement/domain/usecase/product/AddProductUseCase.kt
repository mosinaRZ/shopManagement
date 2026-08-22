package ir.hamedan.shopmanagement.domain.usecase.product

import ir.hamedan.shopmanagement.domain.model.Product
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Long {
        require(product.name.isNotBlank()) { "نام کالا الزامی است" }
        require(product.sellPrice >= 0) { "قیمت فروش نامعتبر است" }
        return repository.addProduct(product)
    }
}