package ir.hamedan.shopmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sales",
    indices = [Index(value = ["productId"]), Index(value = ["customerId"])]
)
data class SaleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productId: Long,
    val productName: String,
    val customerId: Long?,
    val quantity: Int,
    val unitPrice: Double,
    val discount: Double,
    val paidAmount: Double,
    val saleDate: Long,
    val createdAt: Long
)