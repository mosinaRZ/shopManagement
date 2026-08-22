package ir.hamedan.shopmanagement.domain.usecase.product

import ir.hamedan.shopmanagement.domain.model.Product
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> = repository.getAllProducts()
}