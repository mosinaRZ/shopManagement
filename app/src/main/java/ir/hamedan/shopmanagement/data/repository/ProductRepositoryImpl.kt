package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.ProductDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toDomainList
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Product
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> =
        dao.getAll().map { it.toDomainList() }

    override fun getLowStockProducts(): Flow<List<Product>> =
        dao.getLowStock().map { it.toDomainList() }

    override suspend fun getProductById(id: Long): Product? =
        dao.getById(id)?.toDomain()

    override suspend fun addProduct(product: Product): Long =
        dao.insert(product.toEntity())

    override suspend fun updateProduct(product: Product) {
        dao.update(product.toEntity())
    }

    override suspend fun deleteProduct(product: Product) {
        dao.delete(product.toEntity())
    }

    override suspend fun adjustQuantity(productId: Long, delta: Int) {
        dao.adjustQuantity(productId, delta)
    }
}