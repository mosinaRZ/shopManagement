package ir.hamedan.shopmanagement.feature.finance.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.utils.CurrencyUtils
import ir.hamedan.shopmanagement.core.utils.DateUtils
import ir.hamedan.shopmanagement.domain.model.Expense

val sampleExpenses = listOf(
    Expense(id = 1, title = "اجاره مغازه ماهانه", amount = 15000000.0, category = "اجاره", description = "واریز به صاحب ملک", date = System.currentTimeMillis() - 86400000 * 2),
    Expense(id = 2, title = "قبض برق و آب", amount = 850000.0, category = "قبوض", description = null, date = System.currentTimeMillis() - 86400000 * 5),
    Expense(id = 3, title = "خرید کیسه نایلون و چسب", amount = 350000.0, category = "ملزومات", description = null, date = System.currentTimeMillis() - 86400000 * 8)
)

@Composable
fun ExpenseListSection(
    expenses: List<Expense>,
    onAddExpenseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "هزینه‌های جاری فروشگاه",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            FilledTonalButton(
                onClick = onAddExpenseClick,
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("ثبت هزینه", fontSize = 12.sp)
            }
        }

        expenses.forEach { exp ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = exp.title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(text = "دسته: ${exp.category} | ${DateUtils.formatPersianDate(exp.date)}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }

                    Text(
                        text = CurrencyUtils.formatPrice(exp.amount),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}