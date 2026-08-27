package ir.hamedan.shopmanagement.feature.customers.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.ui.components.NeumorphicCard
import ir.hamedan.shopmanagement.core.utils.CurrencyUtils
import ir.hamedan.shopmanagement.domain.model.Customer

@Composable
fun CustomerCardItem(
    customer: Customer,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeumorphicCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        cornerRadius = 24.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // سربرگ: آواتار + نام و نام خانوادگی + دکمه‌های ویرایش و حذف
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(
                                if (customer.gender == "female") Color(0xFFFFE4E6) else Color(0xFFDBEAFE)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = customer.name.take(1),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (customer.gender == "female") Color(0xFFBE123C) else Color(0xFF1D4ED8)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = customer.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        customer.phone?.let {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Phone,
                                    contentDescription = null,
                                    modifier = Modifier.size(12.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = it,
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }

                // دکمه‌های ویرایش و حذف
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    IconButton(
                        onClick = onEditClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "ویرایش",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFF2563EB)
                        )
                    }
                    IconButton(
                        onClick = onDeleteClick,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "حذف",
                            modifier = Modifier.size(16.dp),
                            tint = Color(0xFFEF4444)
                        )
                    }
                }
            }

            Divider(color = Color(0xFFE2E8F0), thickness = 1.dp)

            // اطلاعات تکمیلی: سن و تاریخ تولد + آخرین تراکنش
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("تاریخ تولد / سن:", fontSize = 11.sp, color = Color.Gray)
                    Text(
                        text = "${customer.birthDate ?: "نامشخص"} (${customer.age ?: "--"} سال)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text("آخرین تراکنش:", fontSize = 11.sp, color = Color.Gray)
                    Text(
                        text = customer.lastTransactionDate ?: "بدون تراکنش",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // یادداشت خلاصه (در صورت وجود)
            customer.note?.takeIf { it.isNotBlank() }?.let { noteText ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE2E8F0).copy(alpha = 0.5f))
                        .padding(8.dp)
                ) {
                    Text(
                        text = noteText,
                        fontSize = 11.sp,
                        color = Color.DarkGray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // وضعیت بدهی
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (customer.debt > 0) {
                    Text(
                        text = "بدهکار: ${CurrencyUtils.formatPrice(customer.debt)}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDC2626)
                    )
                } else {
                    Text(
                        text = "تسویه شده",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16A34A)
                    )
                }

                Text(
                    text = "جزئیات ←",
                    fontSize = 11.sp,
                    color = Color(0xFF2563EB),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}