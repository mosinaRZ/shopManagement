package ir.hamedan.shopmanagement.domain.model

/**
 * مدل دامنه‌ی فروش. productName به صورت snapshot ذخیره می‌شود تا حتی اگر محصول
 * بعداً ویرایش/حذف شود، تاریخچه‌ی فاکتور دست‌نخورده بماند.
 * unitPrice هم snapshot قیمت لحظه‌ی فروش است (نه قیمت فعلی محصول).
 */
data class Sale(
    val id: Long = 0L,
    val productId: Long,
    val productName: String,
    val customerId: Long?,
    val quantity: Int,
    val unitPrice: Double,
    val discount: Double = 0.0,
    val paidAmount: Double,
    val saleDate: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
) {
    val totalAmount: Double
        get() = (quantity * unitPrice) - discount

    val remainingAmount: Double
        get() = (totalAmount - paidAmount).coerceAtLeast(0.0)

    val isFullyPaid: Boolean
        get() = paidAmount >= totalAmount
}