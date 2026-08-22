package ir.hamedan.shopmanagement.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hamedan.shopmanagement.data.local.dao.*
import ir.hamedan.shopmanagement.data.local.entity.*

@Database(
    entities = [
        ProductEntity::class,
        CustomerEntity::class,
        SupplierEntity::class,
        SaleEntity::class,
        PurchaseEntity::class,
        ExpenseEntity::class,
        IncomeEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun supplierDao(): SupplierDao
    abstract fun saleDao(): SaleDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao
    abstract fun userDao(): UserDao
}