package ir.hamedan.shopmanagement.data.repository

import androidx.room.withTransaction
import ir.hamedan.shopmanagement.core.database.AppDatabase
import ir.hamedan.shopmanagement.data.local.dao.CustomerDao
import ir.hamedan.shopmanagement.data.local.dao.ProductDao
import ir.hamedan.shopmanagement.data.local.dao.SaleDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.domain.repository.SaleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaleRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val saleDao: SaleDao,
    private val productDao: ProductDao,
    private val customerDao: CustomerDao
) : SaleRepository {

    override fun getAllSales(): Flow<List<Sale>> =
        saleDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getSalesByDateRange(start: Long, end: Long): Flow<List<Sale>> =
        saleDao.getByDateRange(start, end).map { list -> list.map { it.toDomain() } }

    override fun getSalesByCustomer(customerId: Long): Flow<List<Sale>> =
        saleDao.getByCustomer(customerId).map { list -> list.map { it.toDomain() } }

    override suspend fun getSaleById(id: Long): Sale? =
        saleDao.getById(id)?.toDomain()

    override suspend fun addSale(sale: Sale): Long {
        // اجرای اتمیک همه عملیات در قالب یک تراکنش واحد دیتابیس
        return database.withTransaction {
            val saleId = saleDao.insert(sale.toEntity())

            // ۱. کسر از انبار
            productDao.adjustQuantity(sale.productId, -sale.quantity)

            // ۲. محاسبه بدهی مانده و افزودن به حساب مشتری (در صورت وجود مشتری)
            if (sale.customerId != null) {
                val totalAmount = (sale.quantity * sale.unitPrice) - sale.discount
                val remainingDebt = totalAmount - sale.paidAmount
                if (remainingDebt > 0) {
                    customerDao.adjustDebt(sale.customerId, remainingDebt)
                }
            }
            saleId
        }
    }

    override suspend fun deleteSale(sale: Sale) {
        database.withTransaction {
            saleDao.delete(sale.toEntity())
            // بازگرداندن موجودی کالا به انبار
            productDao.adjustQuantity(sale.productId, sale.quantity)
            // اصلاح بدهی مشتری در صورت وجود
            if (sale.customerId != null) {
                val totalAmount = (sale.quantity * sale.unitPrice) - sale.discount
                val remainingDebt = totalAmount - sale.paidAmount
                if (remainingDebt > 0) {
                    customerDao.adjustDebt(sale.customerId, -remainingDebt)
                }
            }
        }
    }

    override suspend fun getTotalSalesAmount(start: Long, end: Long): Double =
        saleDao.getTotalSalesAmount(start, end)
}