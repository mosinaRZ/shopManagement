package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.data.local.dao.ExpenseDao
import ir.hamedan.shopmanagement.data.local.dao.IncomeDao
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.data.mapper.toEntity
import ir.hamedan.shopmanagement.domain.model.Expense
import ir.hamedan.shopmanagement.domain.model.Income
import ir.hamedan.shopmanagement.domain.repository.FinanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinanceRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val incomeDao: IncomeDao
) : FinanceRepository {

    override fun getAllExpenses(): Flow<List<Expense>> =
        expenseDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getExpensesByDateRange(start: Long, end: Long): Flow<List<Expense>> =
        expenseDao.getByDateRange(start, end).map { list -> list.map { it.toDomain() } }

    override suspend fun addExpense(expense: Expense): Long =
        expenseDao.insert(expense.toEntity())

    override suspend fun deleteExpense(expense: Expense) =
        expenseDao.delete(expense.toEntity())

    override suspend fun getTotalExpenses(start: Long, end: Long): Double =
        expenseDao.getTotalExpense(start, end)

    override fun getAllIncomes(): Flow<List<Income>> =
        incomeDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getIncomesByDateRange(start: Long, end: Long): Flow<List<Income>> =
        incomeDao.getByDateRange(start, end).map { list -> list.map { it.toDomain() } }

    override suspend fun addIncome(income: Income): Long =
        incomeDao.insert(income.toEntity())

    override suspend fun deleteIncome(income: Income) =
        incomeDao.delete(income.toEntity())

    override suspend fun getTotalIncomes(start: Long, end: Long): Double =
        incomeDao.getTotalIncome(start, end)
}