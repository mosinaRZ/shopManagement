package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.navigation.NavigationManager
import ir.hamedan.shopmanagement.core.navigation.Routes
import kotlinx.coroutines.launch

data class ShortcutItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val containerColor: Color,
    val iconColor: Color,
    val route: String
)

@Composable
fun QuickShortcutsSection(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    val shortcuts = listOf(
        ShortcutItem(
            title = "انبار و کالاها",
            subtitle = "موجودی و قیمت‌ها",
            icon = Icons.Default.Inventory2,
            containerColor = Color(0xFFE0F2FE),
            iconColor = Color(0xFF0284C7),
            route = Routes.Inventory.route
        ),
        ShortcutItem(
            title = "صدور فاکتور",
            subtitle = "فروش سریع به مشتری",
            icon = Icons.Default.PointOfSale,
            containerColor = Color(0xFFDCFCE7),
            iconColor = Color(0xFF16A34A),
            route = Routes.AddTransaction.route
        ),
        ShortcutItem(
            title = "مشتریان و نسیه",
            subtitle = "دفتر حساب و بدهی",
            icon = Icons.Default.People,
            containerColor = Color(0xFFFEE2E2),
            iconColor = Color(0xFFDC2626),
            route = Routes.Customers.route
        ),
        ShortcutItem(
            title = "خرید از تامین‌کننده",
            subtitle = "شارژ موجودی انبار",
            icon = Icons.Default.ShoppingCartCheckout,
            containerColor = Color(0xFFFEF3C7),
            iconColor = Color(0xFFD97706),
            route = Routes.Purchases.route
        ),
        ShortcutItem(
            title = "امور مالی و سود",
            subtitle = "هزینه‌ها و گزارشات",
            icon = Icons.Default.AccountBalanceWallet,
            containerColor = Color(0xFFF3E8FF),
            iconColor = Color(0xFF9333EA),
            route = Routes.Reports.route
        ),
        ShortcutItem(
            title = "کارکنان و حقوق",
            subtitle = "پرسنل و دستمزد",
            icon = Icons.Default.Badge,
            containerColor = Color(0xFFFFEDD5),
            iconColor = Color(0xFFEA580C),
            route = Routes.Employees.route
        ),
        ShortcutItem(
            title = "تنظیمات فروشگاه",
            subtitle = "پشتیبان‌گیری و ارز",
            icon = Icons.Default.Settings,
            containerColor = Color(0xFFF1F5F9),
            iconColor = Color(0xFF475569),
            route = Routes.Settings.route
        ),
        ShortcutItem(
            title = "ورود / حساب",
            subtitle = "مدیریت دسترسی",
            icon = Icons.Default.AccountCircle,
            containerColor = Color(0xFFE2E8F0),
            iconColor = Color(0xFF334155),
            route = Routes.Login.route
        )
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "دسترسی سریع به بخش‌ها",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "${shortcuts.size} بخش",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // چیدمان ۲ ستونه میانبرها
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            shortcuts.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) {
                            ShortcutCard(
                                item = item,
                                onClick = {
                                    scope.launch {
                                        NavigationManager.navigateTo(item.route)
                                    }
                                }
                            )
                        }
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun ShortcutCard(
    item: ShortcutItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(item.containerColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = item.title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = item.subtitle,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}