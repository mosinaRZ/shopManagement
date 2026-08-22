package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.*
import ir.hamedan.shopmanagement.domain.repository.DashboardRepository
import ir.hamedan.shopmanagement.domain.repository.DashboardSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepositoryImpl @Inject constructor(
    private val saleDao: SaleDao,
    private val purchaseDao: PurchaseDao,
    private val productDao: ProductDao,
    private val customerDao: CustomerDao
) : DashboardRepository {

    override fun getDashboardSummary(): Flow<DashboardSummary> {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfDay = calendar.timeInMillis
        val endOfDay = startOfDay + (24 * 60 * 60 * 1000) - 1

        return combine(
            saleDao.getByDateRange(startOfDay, endOfDay),
            purchaseDao.getByDateRange(startOfDay, endOfDay),
            productDao.getLowStock(),
            customerDao.getAll()
        ) { sales, purchases, lowStock, customers ->
            val salesTotal = sales.sumOf { (it.quantity * it.unitPrice) - it.discount }
            val purchasesTotal = purchases.sumOf { it.quantity * it.unitCost }
            val totalDebt = customers.sumOf { it.debt }

            DashboardSummary(
                totalSalesToday = salesTotal,
                totalPurchasesToday = purchasesTotal,
                totalProfitToday = salesTotal - purchasesTotal,
                lowStockCount = lowStock.size,
                totalCustomersDebt = totalDebt
            )
        }
    }
}