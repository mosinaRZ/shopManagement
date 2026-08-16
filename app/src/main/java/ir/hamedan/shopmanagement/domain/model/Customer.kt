package ir.hamedan.shopmanagement.domain.model

data class Customer(
    val id: Long = 0L,
    val name: String,
    val phone: String? = null,
    val debt: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)