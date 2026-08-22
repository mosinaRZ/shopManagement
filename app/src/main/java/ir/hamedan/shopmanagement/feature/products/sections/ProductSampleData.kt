package ir.hamedan.shopmanagement.feature.products.sections

import ir.hamedan.shopmanagement.domain.model.Product

val sampleProducts = listOf(
    Product(
        id = 1,
        name = "روغن زیتون فرابکر ۱ لیتری",
        category = "مواد غذایی",
        quantity = 4,
        purchasePrice = 320000.0,
        sellPrice = 410000.0,
        minQuantityAlert = 5,
        createdAt = System.currentTimeMillis()
    ),
    Product(
        id = 2,
        name = "برنج طارم هاشمی ۱۰ کیلویی",
        category = "غلات و حبوبات",
        quantity = 18,
        purchasePrice = 1100000.0,
        sellPrice = 1350000.0,
        minQuantityAlert = 5,
        createdAt = System.currentTimeMillis()
    ),
    Product(
        id = 3,
        name = "چای سیلان معطر ۴۰۰ گرمی",
        category = "نوشیدنی",
        quantity = 2,
        purchasePrice = 210000.0,
        sellPrice = 280000.0,
        minQuantityAlert = 6,
        createdAt = System.currentTimeMillis()
    ),
    Product(
        id = 4,
        name = "زعفران یک مثقالی قائنات",
        category = "چاشنی و ادویه",
        quantity = 12,
        purchasePrice = 450000.0,
        sellPrice = 590000.0,
        minQuantityAlert = 3,
        createdAt = System.currentTimeMillis()
    ),
    Product(
        id = 5,
        name = "شکلات تلخ ۸۵٪ پارمیدا",
        category = "تنقلات",
        quantity = 25,
        purchasePrice = 65000.0,
        sellPrice = 90000.0,
        minQuantityAlert = 5,
        createdAt = System.currentTimeMillis()
    )
)