package ir.hamedan.shopmanagement.feature.sales

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.domain.model.Sale
import ir.hamedan.shopmanagement.feature.sales.sections.RecentSalesListSection
import ir.hamedan.shopmanagement.feature.sales.sections.SaleFormSection

val sampleSales = listOf(
    Sale(id = 1, productId = 1, productName = "روغن زیتون فرابکر ۱ لیتری", customerId = 1, quantity = 2, unitPrice = 410000.0, discount = 20000.0, paidAmount = 800000.0, saleDate = System.currentTimeMillis(), createdAt = System.currentTimeMillis()),
    Sale(id = 2, productId = 2, productName = "برنج طارم هاشمی ۱۰ کیلویی", customerId = null, quantity = 1, unitPrice = 1350000.0, discount = 0.0, paidAmount = 1350000.0, saleDate = System.currentTimeMillis() - 3600000, createdAt = System.currentTimeMillis()),
    Sale(id = 3, productId = 4, productName = "زعفران یک مثقالی قائنات", customerId = 3, quantity = 3, unitPrice = 590000.0, discount = 50000.0, paidAmount = 1000000.0, saleDate = System.currentTimeMillis() - 86400000, createdAt = System.currentTimeMillis())
)

@Composable
fun SalesScreen(modifier: Modifier = Modifier) {
    var salesList by remember { mutableStateOf(sampleSales) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SaleFormSection(
            onSaveSale = { name, qty, price, disc, paid ->
                val newSale = Sale(
                    id = System.currentTimeMillis(),
                    productId = 0,
                    productName = name,
                    customerId = null,
                    quantity = qty,
                    unitPrice = price,
                    discount = disc,
                    paidAmount = paid,
                    saleDate = System.currentTimeMillis(),
                    createdAt = System.currentTimeMillis()
                )
                salesList = listOf(newSale) + salesList
            }
        )

        RecentSalesListSection(sales = salesList)
    }
}