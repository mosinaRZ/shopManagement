package ir.hamedan.shopmanagement.feature.customers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.domain.model.Customer
import ir.hamedan.shopmanagement.feature.customers.sections.*

@Composable
fun CustomerScreen(modifier: Modifier = Modifier) {
    var customers by remember { mutableStateOf(sampleCustomers) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedCustomerForDetails by remember { mutableStateOf<Customer?>(null) }
    var customerToEdit by remember { mutableStateOf<Customer?>(null) }

    val filteredCustomers = remember(searchQuery, customers) {
        if (searchQuery.isBlank()) customers
        else customers.filter {
            it.name.contains(searchQuery, true) ||
                    it.phone?.contains(searchQuery) == true ||
                    it.note?.contains(searchQuery, true) == true
        }
    }

    LazyVerticalGrid(
        // دو کارد در هر سطر
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // هدر آمار و دکمه مشتری جدید (span کل سطر را می‌گیرد)
        item(span = { GridItemSpan(2) }) {
            CustomerStatsHeader(
                customers = customers,
                onAddClick = { /* باز کردن فرم مشتری جدید */ }
            )
        }

        // فیلد جستجو
        item(span = { GridItemSpan(2) }) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("جستجوی نام مشتری، یادداشت یا شماره تماس...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                singleLine = true
            )
        }

        // کارت‌های مشتریان
        items(filteredCustomers, key = { it.id }) { customer ->
            CustomerCardItem(
                customer = customer,
                onClick = { selectedCustomerForDetails = customer },
                onEditClick = { customerToEdit = customer },
                onDeleteClick = {
                    customers = customers.filter { it.id != customer.id }
                }
            )
        }
    }

    // دیالوگ نمایش کامل اطلاعات با کلیک روی کارت
    selectedCustomerForDetails?.let { cust ->
        CustomerDetailBottomSheet(
            customer = cust,
            onDismiss = { selectedCustomerForDetails = null }
        )
    }
}