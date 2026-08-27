package ir.hamedan.shopmanagement.domain.model

data class Customer(
    val id: Long,
    val name: String,                    // نام و نام خانوادگی
    val phone: String? = null,           // شماره تماس
    val gender: String = "male",         // جنسیت (male / female)
    val birthDate: String? = null,       // تاریخ تولد مثلا ۱۳۷۰/۰۵/۱۲
    val age: Int? = null,                // سن
    val note: String? = null,            // یادداشت
    val debt: Double = 0.0,              // مانده بدهی
    val lastTransactionDate: String? = null, // تاریخ آخرین تراکنش
    val createdAt: Long = System.currentTimeMillis()
)