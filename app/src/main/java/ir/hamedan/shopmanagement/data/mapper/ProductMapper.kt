package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.ProductEntity
import ir.hamedan.shopmanagement.domain.model.Product

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    category = category,
    quantity = quantity,
    purchasePrice = purchasePrice,
    sellPrice = sellPrice,
    minQuantityAlert = minQuantityAlert,
    createdAt = createdAt
)

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    category = category,
    quantity = quantity,
    purchasePrice = purchasePrice,
    sellPrice = sellPrice,
    minQuantityAlert = minQuantityAlert,
    createdAt = createdAt
)

fun List<ProductEntity>.toDomainList(): List<Product> = map { it.toDomain() }