package ir.hamedan.shopmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    indices = [Index(value = ["name"])]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val category: String?,
    val quantity: Int,
    val purchasePrice: Double,
    val sellPrice: Double,
    val minQuantityAlert: Int,
    val createdAt: Long
)