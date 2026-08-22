package ir.hamedan.shopmanagement.domain.usecase.dashboard

import ir.hamedan.shopmanagement.domain.repository.DashboardRepository
import ir.hamedan.shopmanagement.domain.repository.DashboardSummary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDashboardDataUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    operator fun invoke(): Flow<DashboardSummary> = repository.getDashboardSummary()
}