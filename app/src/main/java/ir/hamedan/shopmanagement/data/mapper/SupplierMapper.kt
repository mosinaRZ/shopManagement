package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.SupplierEntity
import ir.hamedan.shopmanagement.domain.model.Supplier

fun SupplierEntity.toDomain() = Supplier(
    id = id,
    name = name,
    phone = phone,
    company = company,
    debt = debt,
    createdAt = createdAt
)

fun Supplier.toEntity() = SupplierEntity(
    id = id,
    name = name,
    phone = phone,
    company = company,
    debt = debt,
    createdAt = createdAt
)