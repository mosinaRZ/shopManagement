package ir.hamedan.shopmanagement.core.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddBox
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.navigation.Routes

data class QuickActionItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val iconBgColor: Color
)

@Composable
fun QuickAddOverlay(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onActionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // انیمیشن محو شدن پس‌زمینه
    val backdropAlpha by animateFloatAsState(
        targetValue = if (expanded) 0.55f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "backdropAlpha"
    )

    // انیمیشن کلی باز شدن منو (از ۰ تا ۱ با افکت فنری)
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "menuProgress"
    )

    if (backdropAlpha > 0f) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = backdropAlpha))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.BottomCenter
        ) {

            // لیست اکشن‌های سریع
            val actions = listOf(
                QuickActionItem("فاکتور جدید", Icons.Rounded.ReceiptLong, Routes.AddTransaction.route, Color(0xFF4CAF50)),
                QuickActionItem("تراکنش جدید", Icons.Rounded.Paid, Routes.AddTransaction.route, Color(0xFF2196F3)),
                QuickActionItem("مشتری جدید", Icons.Rounded.PersonAdd, Routes.Customers.route, Color(0xFFFF9800)),
                QuickActionItem("کالای جدید", Icons.Rounded.AddBox, Routes.Inventory.route, Color(0xFF9C27B0))
            )

            // شبکه ۲در۲ از کارت‌های مربع نئومورفیسمی
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = 100.dp) // فاصله مناسب از بالای باتم‌بار
                    .graphicsLayer {
                        // جابه‌جایی عمودی: کارت‌ها از پشت دکمه (پائین) به سمت بالا پرتاب می‌شوند
                        translationY = (1f - progress) * 220.dp.toPx()
                        scaleX = 0.3f + (0.7f * progress)
                        scaleY = 0.3f + (0.7f * progress)
                        alpha = progress.coerceIn(0f, 1f)
                    }
            ) {
                // سطر اول (۲ کارت)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    QuickActionCard(item = actions[0], onClick = { onActionClick(actions[0].route) })
                    QuickActionCard(item = actions[1], onClick = { onActionClick(actions[1].route) })
                }

                // سطر دوم (۲ کارت)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    QuickActionCard(item = actions[2], onClick = { onActionClick(actions[2].route) })
                    QuickActionCard(item = actions[3], onClick = { onActionClick(actions[3].route) })
                }
            }
        }
    }
}

@Composable
private fun QuickActionCard(
    item: QuickActionItem,
    onClick: () -> Unit
) {
    NeumorphicCard(
        cornerRadius = 24.dp,
        modifier = Modifier
            .size(125.dp) // کارت‌های مربعی شکل
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(item.iconBgColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconBgColor,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}