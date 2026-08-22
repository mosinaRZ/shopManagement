package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.ExpenseEntity
import ir.hamedan.shopmanagement.data.local.entity.IncomeEntity
import ir.hamedan.shopmanagement.domain.model.Expense
import ir.hamedan.shopmanagement.domain.model.Income

fun ExpenseEntity.toDomain() = Expense(id, title, amount, category, description, date, createdAt)
fun Expense.toEntity() = ExpenseEntity(id, title, amount, category, description, date, createdAt)

fun IncomeEntity.toDomain() = Income(id, title, amount, category, description, date, createdAt)
fun Income.toEntity() = IncomeEntity(id, title, amount, category, description, date, createdAt)