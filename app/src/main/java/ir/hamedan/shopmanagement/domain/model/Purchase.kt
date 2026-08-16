package ir.hamedan.shopmanagement.domain.model

data class Purchase(
    val id: Long = 0L,
    val productId: Long,
    val productName: String,
    val supplierId: Long?,
    val quantity: Int,
    val unitCost: Double,
    val paidAmount: Double,
    val purchaseDate: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
) {
    val totalAmount: Double
        get() = quantity * unitCost

    val remainingAmount: Double
        get() = (totalAmount - paidAmount).coerceAtLeast(0.0)

    val isFullyPaid: Boolean
        get() = paidAmount >= totalAmount
}