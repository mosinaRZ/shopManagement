package ir.hamedan.shopmanagement.feature.purchases.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.utils.CurrencyUtils

@Composable
fun PurchaseFormSection(
    onSavePurchase: (String, Int, Double, Double) -> Unit,
    modifier: Modifier = Modifier
) {
    var productName by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("1") }
    var unitCostText by remember { mutableStateOf("") }
    var paidAmountText by remember { mutableStateOf("") }

    val quantity = quantityText.toIntOrNull() ?: 1
    val unitCost = unitCostText.toDoubleOrNull() ?: 0.0
    val totalCost = quantity * unitCost

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "ثبت خرید از تامین‌کننده (شارژ انبار)",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("نام کالا / بار خریداری‌شده *") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = quantityText,
                    onValueChange = { quantityText = it },
                    label = { Text("تعداد") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = unitCostText,
                    onValueChange = {
                        unitCostText = it
                        paidAmountText = ((quantityText.toIntOrNull() ?: 1) * (it.toDoubleOrNull() ?: 0.0)).toLong().toString()
                    },
                    label = { Text("قیمت خرید هر واحد (تومان)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(2f),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
            }

            OutlinedTextField(
                value = paidAmountText,
                onValueChange = { paidAmountText = it },
                label = { Text("مبلغ پرداختی نقدی (تومان)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("جمع کل فاکتور خرید:", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text(
                        text = CurrencyUtils.formatPrice(if (totalCost > 0) totalCost else 0.0),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Button(
                onClick = {
                    if (productName.isNotBlank() && unitCost > 0) {
                        val paid = paidAmountText.toDoubleOrNull() ?: totalCost
                        onSavePurchase(productName, quantity, unitCost, paid)
                        productName = ""
                        unitCostText = ""
                        paidAmountText = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = productName.isNotBlank() && unitCost > 0
            ) {
                Icon(Icons.Default.AddShoppingCart, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("ثبت فاکتور خرید و افزایش موجودی", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}