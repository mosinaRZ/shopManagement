package ir.hamedan.shopmanagement.feature.purchases.sections

import ir.hamedan.shopmanagement.domain.model.Purchase

val samplePurchases = listOf(
    Purchase(
        id = 1,
        productId = 2,
        productName = "برنج طارم هاشمی ۱۰ کیلویی",
        supplierId = 1,
        quantity = 20,
        unitCost = 1100000.0,
        paidAmount = 15000000.0,
        purchaseDate = System.currentTimeMillis() - 86400000,
        createdAt = System.currentTimeMillis()
    ),
    Purchase(
        id = 2,
        productId = 1,
        productName = "روغن زیتون فرابکر ۱ لیتری",
        supplierId = 2,
        quantity = 30,
        unitCost = 320000.0,
        paidAmount = 9600000.0,
        purchaseDate = System.currentTimeMillis() - (86400000 * 3),
        createdAt = System.currentTimeMillis()
    )
)