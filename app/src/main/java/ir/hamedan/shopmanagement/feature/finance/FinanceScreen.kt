package ir.hamedan.shopmanagement.feature.finance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.feature.finance.sections.ExpenseListSection
import ir.hamedan.shopmanagement.feature.finance.sections.ProfitLossCard
import ir.hamedan.shopmanagement.feature.finance.sections.sampleExpenses

@Composable
fun FinanceScreen(modifier: Modifier = Modifier) {
    var expenses by remember { mutableStateOf(sampleExpenses) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ProfitLossCard(
            income = 48500000.0,
            expense = 26200000.0
        )

        ExpenseListSection(
            expenses = expenses,
            onAddExpenseClick = { /* دیالوگ ثبت هزینه */ }
        )
    }
}