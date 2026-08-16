package ir.hamedan.shopmanagement.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.hamedan.shopmanagement.data.repository.AuthRepositoryImpl
import ir.hamedan.shopmanagement.data.repository.CustomerRepositoryImpl
import ir.hamedan.shopmanagement.data.repository.ProductRepositoryImpl
import ir.hamedan.shopmanagement.data.repository.PurchaseRepositoryImpl
import ir.hamedan.shopmanagement.data.repository.SaleRepositoryImpl
import ir.hamedan.shopmanagement.domain.repository.AuthRepository
import ir.hamedan.shopmanagement.domain.repository.CustomerRepository
import ir.hamedan.shopmanagement.domain.repository.ProductRepository
import ir.hamedan.shopmanagement.domain.repository.PurchaseRepository
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(impl: CustomerRepositoryImpl): CustomerRepository

    @Binds
    @Singleton
    abstract fun bindSaleRepository(impl: SaleRepositoryImpl): SaleRepository

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(impl: PurchaseRepositoryImpl): PurchaseRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}