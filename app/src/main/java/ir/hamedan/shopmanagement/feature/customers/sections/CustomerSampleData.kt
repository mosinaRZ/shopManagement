package ir.hamedan.shopmanagement.feature.customers.sections

import ir.hamedan.shopmanagement.domain.model.Customer

val sampleCustomers = listOf(
    Customer(id = 1, name = "حاج رضا کریمی", phone = "09181112233", debt = 4500000.0, createdAt = System.currentTimeMillis()),
    Customer(id = 2, name = "مهدی علیزاده", phone = "09124445566", debt = 0.0, createdAt = System.currentTimeMillis()),
    Customer(id = 3, name = "خانم سلیمی (سوپرمارکت بهار)", phone = "09357778899", debt = 8200000.0, createdAt = System.currentTimeMillis()),
    Customer(id = 4, name = "سعید مرادی", phone = "09189990011", debt = 650000.0, createdAt = System.currentTimeMillis()),
    Customer(id = 5, name = "امیرحسین رضایی", phone = "09363332211", debt = 0.0, createdAt = System.currentTimeMillis())
)