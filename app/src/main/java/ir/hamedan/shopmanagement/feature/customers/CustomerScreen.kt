package ir.hamedan.shopmanagement.feature.customers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.feature.customers.sections.*

@Composable
fun CustomerScreen(modifier: Modifier = Modifier) {
    var customers by remember { mutableStateOf(sampleCustomers) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredCustomers = remember(searchQuery, customers) {
        if (searchQuery.isBlank()) customers
        else customers.filter { it.name.contains(searchQuery, true) || it.phone?.contains(searchQuery) == true }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            CustomerStatsHeader(
                customers = customers,
                onAddClick = { /* باز کردن باتم شیت مشتری جدید */ }
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("جستجوی مشتری یا شماره تماس...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )
        }

        items(filteredCustomers, key = { it.id }) { customer ->
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                CustomerCardItem(
                    customer = customer,
                    onClick = { /* نمایش جزئیات حساب و فاکتورها */ }
                )
            }
        }
    }
}