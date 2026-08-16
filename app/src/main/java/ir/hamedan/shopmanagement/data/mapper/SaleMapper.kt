package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.SaleEntity
import ir.hamedan.shopmanagement.domain.model.Sale

fun SaleEntity.toDomain(): Sale = Sale(
    id = id,
    productId = productId,
    productName = productName,
    customerId = customerId,
    quantity = quantity,
    unitPrice = unitPrice,
    discount = discount,
    paidAmount = paidAmount,
    saleDate = saleDate,
    createdAt = createdAt
)

fun Sale.toEntity(): SaleEntity = SaleEntity(
    id = id,
    productId = productId,
    productName = productName,
    customerId = customerId,
    quantity = quantity,
    unitPrice = unitPrice,
    discount = discount,
    paidAmount = paidAmount,
    saleDate = saleDate,
    createdAt = createdAt
)

fun List<SaleEntity>.toDomainList(): List<Sale> = map { it.toDomain() }