package ir.hamedan.shopmanagement.domain.repository

import ir.hamedan.shopmanagement.domain.model.Expense
import ir.hamedan.shopmanagement.domain.model.Income
import kotlinx.coroutines.flow.Flow

interface FinanceRepository {
    fun getAllExpenses(): Flow<List<Expense>>
    fun getExpensesByDateRange(start: Long, end: Long): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense): Long
    suspend fun deleteExpense(expense: Expense)
    suspend fun getTotalExpenses(start: Long, end: Long): Double

    fun getAllIncomes(): Flow<List<Income>>
    fun getIncomesByDateRange(start: Long, end: Long): Flow<List<Income>>
    suspend fun addIncome(income: Income): Long
    suspend fun deleteIncome(income: Income)
    suspend fun getTotalIncomes(start: Long, end: Long): Double
}