package ir.hamedan.shopmanagement.feature.employees

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.feature.employees.sections.*

@Composable
fun EmployeeScreen(modifier: Modifier = Modifier) {
    var employees by remember { mutableStateOf(sampleEmployees) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                EmployeeStatsHeader(
                    employees = employees,
                    onAddClick = { /* باز کردن باتم‌شیت پرسنل جدید */ }
                )
            }

            items(employees, key = { it.id }) { employee ->
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    EmployeeCardItem(
                        employee = employee,
                        onDelete = { employees = employees.filter { it.id != employee.id } }
                    )
                }
            }
        }
    }
}