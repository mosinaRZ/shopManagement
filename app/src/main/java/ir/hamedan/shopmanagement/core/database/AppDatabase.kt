package ir.hamedan.shopmanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hamedan.shopmanagement.data.local.dao.CustomerDao
import ir.hamedan.shopmanagement.data.local.dao.ProductDao
import ir.hamedan.shopmanagement.data.local.dao.PurchaseDao
import ir.hamedan.shopmanagement.data.local.dao.SaleDao
import ir.hamedan.shopmanagement.data.local.dao.UserDao
import ir.hamedan.shopmanagement.data.local.entity.CustomerEntity
import ir.hamedan.shopmanagement.data.local.entity.ProductEntity
import ir.hamedan.shopmanagement.data.local.entity.PurchaseEntity
import ir.hamedan.shopmanagement.data.local.entity.SaleEntity
import ir.hamedan.shopmanagement.data.local.entity.UserEntity

/**
 * توجه: با اضافه شدن ماژول‌های Supplier/Expense/Income باید Entity هایشان
 * به این لیست و version باید +1 شود (به همراه Migration مناسب).
 */
@Database(
    entities = [
        ProductEntity::class,
        CustomerEntity::class,
        SaleEntity::class,
        PurchaseEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun saleDao(): SaleDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun userDao(): UserDao
}