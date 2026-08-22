package ir.hamedan.shopmanagement.domain.model

data class Debt(
    val id: Long = 0L,
    val personName: String,
    val personPhone: String? = null,
    val amount: Double,
    val isDebtor: Boolean, // true: طلب ما از دیگری، false: بدهی ما به دیگری
    val dueDate: Long? = null,
    val description: String? = null
)