package ir.hamedan.shopmanagement.domain.model

data class Product(
    val id: Long = 0L,
    val name: String,
    val category: String? = null,
    val quantity: Int,
    val purchasePrice: Double,
    val sellPrice: Double,
    val minQuantityAlert: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) {
    val isLowStock: Boolean
        get() = quantity <= minQuantityAlert
}