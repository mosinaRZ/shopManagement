package ir.hamedan.shopmanagement.feature.employees.sections

import ir.hamedan.shopmanagement.domain.model.Employee

val sampleEmployees = listOf(
    Employee(
        id = 1,
        name = "علی محمدی",
        role = "فروشنده و صندوق‌دار",
        phone = "09183114455",
        salary = 14500000.0,
        hireDate = System.currentTimeMillis() - (86400000L * 180)
    ),
    Employee(
        id = 2,
        name = "رضا احمدی",
        role = "مسئول انبار و چیدمان",
        phone = "09356667788",
        salary = 12000000.0,
        hireDate = System.currentTimeMillis() - (86400000L * 90)
    ),
    Employee(
        id = 3,
        name = "زهرا حسینی",
        role = "حسابدار پاره‌وقت",
        phone = "09129991122",
        salary = 9500000.0,
        hireDate = System.currentTimeMillis() - (86400000L * 300)
    )
)