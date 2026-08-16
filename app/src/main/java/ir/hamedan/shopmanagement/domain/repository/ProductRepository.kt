package ir.hamedan.shopmanagement.domain.repository

import ir.hamedan.shopmanagement.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getLowStockProducts(): Flow<List<Product>>
    suspend fun getProductById(id: Long): Product?
    suspend fun addProduct(product: Product): Long
    suspend fun updateProduct(product: Product)
    suspend fun deleteProduct(product: Product)
    suspend fun adjustQuantity(productId: Long, delta: Int)
}