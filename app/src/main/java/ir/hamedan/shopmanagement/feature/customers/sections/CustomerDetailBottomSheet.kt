package ir.hamedan.shopmanagement.feature.customers.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.utils.CurrencyUtils
import ir.hamedan.shopmanagement.domain.model.Customer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailBottomSheet(
    customer: Customer,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "پرونده کامل مشتری",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // اطلاعات اصلی
            DetailRow(icon = Icons.Default.Person, label = "نام و نام خانوادگی:", value = customer.name)
            DetailRow(icon = Icons.Default.Face, label = "جنسیت:", value = if (customer.gender == "female") "خانم" else "آقا")
            DetailRow(
                icon = Icons.Default.DateRange,
                label = "تاریخ تولد و سن:",
                value = "${customer.birthDate ?: "ثبت نشده"} (سن: ${customer.age ?: "نامشخص"} سال)"
            )
            DetailRow(icon = Icons.Default.Phone, label = "شماره تماس:", value = customer.phone ?: "ثبت نشده")
            DetailRow(icon = Icons.Default.History, label = "تاریخ آخرین تراکنش:", value = customer.lastTransactionDate ?: "بدون سابقه")
            DetailRow(
                icon = Icons.Default.AccountBalanceWallet,
                label = "وضعیت مانده بدهی:",
                value = if (customer.debt > 0) CurrencyUtils.formatPrice(customer.debt) else "تسویه شده"
            )

            // یادداشت
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "یادداشت مشتری:", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = customer.note ?: "هیچ یادداشتی ثبت نشده است.",
                        modifier = Modifier.padding(12.dp),
                        fontSize = 13.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("بستن")
            }
        }
    }
}

@Composable
fun DetailRow(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = label, fontSize = 13.sp, color = Color.Gray)
        }
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}