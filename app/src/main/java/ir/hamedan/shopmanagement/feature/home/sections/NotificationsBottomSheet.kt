package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material.icons.rounded.ReportProblem
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.theme.Info as InfoColor
import ir.hamedan.shopmanagement.core.ui.theme.Success
import ir.hamedan.shopmanagement.core.ui.theme.Warning as WarningColor

data class NotificationModel(
    val id: String,
    val title: String,
    val description: String,
    val type: String, // SUCCESS, ERROR, WARNING, REWARD, INFO
    val isRead: Boolean = false
)

// لیست نمونه جهت تست و نمایش
val sampleNotifications = listOf(
    NotificationModel(
        id = "1",
        title = "ثبت فروش جدید",
        description = "فاکتور شماره ۱۲۸۴ با مبلغ ۱,۲۵۰,۰۰۰ تومان با موفقیت ثبت شد.",
        type = "SUCCESS",
        isRead = false
    ),
    NotificationModel(
        id = "2",
        title = "موجودی رو به اتمام",
        description = "موجودی کالای «روغن موتور ۱۰W۴۰» به کمتر از ۵ عدد رسیده است.",
        type = "WARNING",
        isRead = false
    ),
    NotificationModel(
        id = "3",
        title = "خطا در همگام‌سازی",
        description = "همگام‌سازی داده‌ها با سرور ابری ناموفق بود. اتصال اینترنت را بررسی کنید.",
        type = "ERROR",
        isRead = false
    ),
    NotificationModel(
        id = "4",
        title = "پاداش و تخفیف ویژه",
        description = "تخفیف ویژه فعال‌سازی بسته ۳ ماهه برای فروشگاه شما در نظر گرفته شد.",
        type = "REWARD",
        isRead = false
    ),
    NotificationModel(
        id = "5",
        title = "به‌روزرسانی نرم‌افزار",
        description = "نسخه ۲.۴.۰ برنامه‌ریزی شده و آماده دریافت است.",
        type = "INFO",
        isRead = false
    ),
    NotificationModel(
        id = "6",
        title = "سررسید چک دریافتی",
        description = "چک شماره ۵۸۲۹ متعلق به آقای احمدی فردا سررسید می‌شود.",
        type = "WARNING",
        isRead = true
    ),
    NotificationModel(
        id = "7",
        title = "تغییر قیمت محصولات",
        description = "قیمت ۵ آیتم در انبار بر اساس فاکتور جدید خرید به‌روزرسانی شد.",
        type = "SUCCESS",
        isRead = true
    ),
    NotificationModel(
        id = "8",
        title = "پایان مهلت اشتراک",
        description = "تنها ۳ روز از اعتبار اشتراک ویژه حسابداری شما باقی مانده است.",
        type = "WARNING",
        isRead = true
    ),
    NotificationModel(
        id = "9",
        title = "تأیید مرجوعی کالا",
        description = "درخواست مرجوعی فاکتور ۱۰۴۲ تأیید شد و مبلغ به کیف پول برگشت.",
        type = "SUCCESS",
        isRead = true
    ),
    NotificationModel(
        id = "10",
        title = "ورود جدید به حساب",
        description = "نشست جدیدی از دستگاه سامسونگ S21 در ساعت ۱۴:۳۰ ثبت گردید.",
        type = "INFO",
        isRead = true
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false),
    notifications: List<NotificationModel>,
    unreadCount: Int,
    onDismissRequest: () -> Unit,
    onMarkAsRead: (String) -> Unit = {},
    onMarkAllAsRead: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.Transparent,
        scrimColor = Color.Black.copy(alpha = 0.4f),
        dragHandle = null
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 36.dp, top = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.96f),
                    shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(32.dp)
                )
                .clip(RoundedCornerShape(32.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                // اهرم کشیدن
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 36.dp, height = 4.dp)
                            .background(
                                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                                RoundedCornerShape(2.dp)
                            )
                    )
                }

                // هدر
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "اعلان‌ها و رویدادها",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false)
                    )

                    if (unreadCount > 0) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                        CircleShape
                                    )
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "$unreadCount پیام جدید",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            TextButton(
                                onClick = onMarkAllAsRead,
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "همه را خواندم",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                // لیست اعلان‌ها
                if (notifications.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 64.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Rounded.NotificationsNone,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = "اعلانی وجود ندارد",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                            .padding(horizontal = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp)
                    ) {
                        items(notifications, key = { it.id }) { item ->
                            LaunchedEffect(item.id, item.isRead) {
                                if (!item.isRead) onMarkAsRead(item.id)
                            }

                            val itemShape = RoundedCornerShape(18.dp)
                            val (icon, iconColor) = when (item.type.uppercase()) {
                                "SUCCESS" -> Icons.Rounded.CheckCircle to Success
                                "ERROR" -> Icons.Rounded.ReportProblem to MaterialTheme.colorScheme.error
                                "WARNING" -> Icons.Rounded.Warning to WarningColor
                                "REWARD" -> Icons.Rounded.CardGiftcard to MaterialTheme.colorScheme.tertiary
                                else -> Icons.Rounded.Info to InfoColor
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(itemShape)
                                    .background(
                                        if (!item.isRead)
                                            MaterialTheme.colorScheme.primary.copy(alpha = 0.06f)
                                        else
                                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                                    )
                                    .padding(14.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(iconColor.copy(alpha = 0.12f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = null,
                                        tint = iconColor,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }

                                Spacer(Modifier.width(12.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.weight(1f)
                                        )

                                        if (!item.isRead) {
                                            Box(
                                                modifier = Modifier
                                                    .size(8.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        CircleShape
                                                    )
                                            )
                                        }
                                    }

                                    Spacer(Modifier.height(4.dp))

                                    Text(
                                        text = item.description,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f),
                                        lineHeight = MaterialTheme.typography.bodySmall.lineHeight * 1.2
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))
            }
        }
    }
}