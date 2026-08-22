package ir.hamedan.shopmanagement.domain.model

data class Income(
    val id: Long = 0L,
    val title: String,
    val amount: Double,
    val category: String,
    val description: String? = null,
    val date: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)