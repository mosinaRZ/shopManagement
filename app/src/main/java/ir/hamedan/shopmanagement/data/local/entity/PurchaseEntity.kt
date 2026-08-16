package ir.hamedan.shopmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "purchases",
    indices = [Index(value = ["productId"]), Index(value = ["supplierId"])]
)
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productId: Long,
    val productName: String,
    val supplierId: Long?,
    val quantity: Int,
    val unitCost: Double,
    val paidAmount: Double,
    val purchaseDate: Long,
    val createdAt: Long
)