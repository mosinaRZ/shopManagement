package ir.hamedan.shopmanagement.feature.products

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
import ir.hamedan.shopmanagement.feature.products.sections.*

@Composable
fun ProductScreen(modifier: Modifier = Modifier) {
    var products by remember { mutableStateOf(sampleProducts) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = remember(searchQuery, products) {
        if (searchQuery.isBlank()) products
        else products.filter { it.name.contains(searchQuery, ignoreCase = true) || it.category?.contains(searchQuery, true) == true }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            InventoryStatsHeader(
                products = products,
                onAddClick = { /* باز کردن دیالوگ افزودن */ }
            )
        }

        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("جستجو در بین کالاها...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )
        }

        items(filteredProducts, key = { it.id }) { product ->
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                ProductCardItem(
                    product = product,
                    onEdit = { },
                    onDelete = { products = products.filter { it.id != product.id } }
                )
            }
        }
    }
}