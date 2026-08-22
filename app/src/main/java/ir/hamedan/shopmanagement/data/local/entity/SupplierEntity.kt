package ir.hamedan.shopmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val phone: String?,
    val company: String?,
    val debt: Double = 0.0, // بدهی/طلب ما به تامین‌کننده
    val createdAt: Long = System.currentTimeMillis()
)