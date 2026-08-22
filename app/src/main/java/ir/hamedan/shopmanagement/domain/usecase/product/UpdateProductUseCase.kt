package ir.hamedan.shopmanagement.domain.usecase.product

import ir.hamedan.shopmanagement.domain.model.Product
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) = repository.updateProduct(product)
}