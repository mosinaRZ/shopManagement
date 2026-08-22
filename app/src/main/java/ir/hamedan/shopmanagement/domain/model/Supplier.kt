package ir.hamedan.shopmanagement.domain.model

data class Supplier(
    val id: Long = 0L,
    val name: String,
    val phone: String? = null,
    val company: String? = null,
    val debt: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)