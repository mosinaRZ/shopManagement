package ir.hamedan.shopmanagement.domain.repository

import kotlinx.coroutines.flow.Flow

data class DashboardSummary(
    val totalSalesToday: Double,
    val totalPurchasesToday: Double,
    val totalProfitToday: Double,
    val lowStockCount: Int,
    val totalCustomersDebt: Double
)

interface DashboardRepository {
    fun getDashboardSummary(): Flow<DashboardSummary>
}