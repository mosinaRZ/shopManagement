package ir.hamedan.shopmanagement.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.hamedan.shopmanagement.data.local.AppDatabase
import ir.hamedan.shopmanagement.data.local.dao.CustomerDao
import ir.hamedan.shopmanagement.data.local.dao.ProductDao
import ir.hamedan.shopmanagement.data.local.dao.PurchaseDao
import ir.hamedan.shopmanagement.data.local.dao.SaleDao
import ir.hamedan.shopmanagement.data.local.dao.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "shopmanagement.db")
            // .fallbackToDestructiveMigration() // فقط در توسعه؛ برای production migration واقعی بنویس
            .build()

    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao = db.customerDao()

    @Provides
    fun provideSaleDao(db: AppDatabase): SaleDao = db.saleDao()

    @Provides
    fun providePurchaseDao(db: AppDatabase): PurchaseDao = db.purchaseDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
}