package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.CustomerEntity
import ir.hamedan.shopmanagement.domain.model.Customer

fun CustomerEntity.toDomain(): Customer = Customer(
    id = id,
    name = name,
    phone = phone,
    gender = gender,
    birthDate = birthDate,
    age = calculateAge(birthDate), // محاسبه خودکار سن
    note = note,
    lastTransactionDate = lastTransactionDate,
    debt = debt,
    createdAt = createdAt
)

fun Customer.toEntity(): CustomerEntity = CustomerEntity(
    id = id,
    name = name,
    phone = phone,
    gender = gender,
    birthDate = birthDate,
    note = note,
    lastTransactionDate = lastTransactionDate,
    debt = debt,
    createdAt = createdAt
)

fun List<CustomerEntity>.toDomainList(): List<Customer> = map { it.toDomain() }

// تابع کمکی برای محاسبه سن از روی سال تولد شمسی
private fun calculateAge(birthDateStr: String?): Int? {
    if (birthDateStr.isNullOrBlank()) return null
    return try {
        val yearPart = birthDateStr.split("/", "-", ".").firstOrNull()?.toIntOrNull()
        if (yearPart != null && yearPart in 1300..1450) {
            val currentYear = 1403 // یا سال جاری شمسی
            (currentYear - yearPart).coerceAtLeast(0)
        } else null
    } catch (e: Exception) {
        null
    }
}