package ir.hamedan.shopmanagement.feature.purchases

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.domain.model.Purchase
import ir.hamedan.shopmanagement.feature.purchases.sections.*

@Composable
fun PurchaseScreen(modifier: Modifier = Modifier) {
    var purchasesList by remember { mutableStateOf(samplePurchases) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PurchaseFormSection(
            onSavePurchase = { name, qty, cost, paid ->
                val newPurchase = Purchase(
                    id = System.currentTimeMillis(),
                    productId = 0,
                    productName = name,
                    supplierId = null,
                    quantity = qty,
                    unitCost = cost,
                    paidAmount = paid,
                    purchaseDate = System.currentTimeMillis(),
                    createdAt = System.currentTimeMillis()
                )
                purchasesList = listOf(newPurchase) + purchasesList
            }
        )

        RecentPurchasesListSection(purchases = purchasesList)
    }
}