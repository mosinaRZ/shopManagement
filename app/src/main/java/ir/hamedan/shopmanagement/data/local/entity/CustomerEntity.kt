package ir.hamedan.shopmanagement.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val phone: String? = null,
    val gender: String = "male",              // فیلد جدید: جنسیت (male / female)
    val birthDate: String? = null,            // فیلد جدید: تاریخ تولد (مثلا 1370/05/12)
    val note: String? = null,                 // فیلد جدید: یادداشت
    val lastTransactionDate: String? = null,  // فیلد جدید: تاریخ آخرین تراکنش
    val debt: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)