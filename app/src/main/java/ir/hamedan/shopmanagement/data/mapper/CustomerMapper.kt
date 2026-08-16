package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.CustomerEntity
import ir.hamedan.shopmanagement.domain.model.Customer

fun CustomerEntity.toDomain(): Customer = Customer(
    id = id,
    name = name,
    phone = phone,
    debt = debt,
    createdAt = createdAt
)

fun Customer.toEntity(): CustomerEntity = CustomerEntity(
    id = id,
    name = name,
    phone = phone,
    debt = debt,
    createdAt = createdAt
)

fun List<CustomerEntity>.toDomainList(): List<Customer> = map { it.toDomain() }