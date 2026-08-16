package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.PurchaseEntity
import ir.hamedan.shopmanagement.domain.model.Purchase

fun PurchaseEntity.toDomain(): Purchase = Purchase(
    id = id,
    productId = productId,
    productName = productName,
    supplierId = supplierId,
    quantity = quantity,
    unitCost = unitCost,
    paidAmount = paidAmount,
    purchaseDate = purchaseDate,
    createdAt = createdAt
)

fun Purchase.toEntity(): PurchaseEntity = PurchaseEntity(
    id = id,
    productId = productId,
    productName = productName,
    supplierId = supplierId,
    quantity = quantity,
    unitCost = unitCost,
    paidAmount = paidAmount,
    purchaseDate = purchaseDate,
    createdAt = createdAt
)

fun List<PurchaseEntity>.toDomainList(): List<Purchase> = map { it.toDomain() }