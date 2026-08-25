package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.navigation.NavigationManager
import ir.hamedan.shopmanagement.core.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    sheetProgress: Float = 0f,
    onNotificationsClick: () -> Unit = {}
) {
    val progress = sheetProgress.coerceIn(0f, 1f)

    val scope = rememberCoroutineScope()

    /*
     * SmoothStep
     *
     * شروع → آرام
     * وسط → سریع‌تر
     * پایان → دوباره آرام
     */
    val easedProgress = progress * progress * (3f - 2f * progress)

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer
            )
    ) {

        val width = maxWidth
        val height = maxHeight

        // =========================================================
        // TOP BAR
        // =========================================================
        // هم‌زمان با بالا آمدن Sheet کاملاً محو می‌شود

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .graphicsLayer {
                    alpha = 1f - easedProgress
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme
                            .onPrimaryContainer
                            .copy(alpha = 0.1f)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable {
                        scope.launch {
                            NavigationManager.navigateTo(Routes.Profile.route)
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "حساب کاربری",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "حساب: سینا رمضانی",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { onNotificationsClick() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme
                            .onPrimaryContainer
                            .copy(alpha = 0.1f)
                    )
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "اعلان‌ها",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        // =========================================================
        // FLOATING ICONS
        // =========================================================

        FloatingHeaderIcon(
            icon = Icons.Rounded.ShoppingCart,
            startX = 0.14f,
            startY = 0.39f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 28.dp,
            rotation = -20f
        )

        FloatingHeaderIcon(
            icon = Icons.Rounded.AccountBalanceWallet,
            startX = 0.84f,
            startY = 0.36f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 24.dp,
            rotation = 22f
        )

        FloatingHeaderIcon(
            icon = Icons.Rounded.Notifications,
            startX = 0.13f,
            startY = 0.58f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 23.dp,
            rotation = -28f
        )

        FloatingHeaderIcon(
            icon = Icons.Rounded.Settings,
            startX = 0.86f,
            startY = 0.58f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 25.dp,
            rotation = 28f
        )

        FloatingHeaderIcon(
            icon = Icons.Rounded.Sell,
            startX = 0.28f,
            startY = 0.30f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 20.dp,
            rotation = -15f
        )

        FloatingHeaderIcon(
            icon = Icons.Rounded.Money,
            startX = 0.72f,
            startY = 0.29f,
            targetX = 0.50f,
            targetY = 0.47f,
            progress = progress,
            containerWidth = width,
            containerHeight = height,
            size = 19.dp,
            rotation = 16f
        )

        // =========================================================
        // MAIN HEADER CONTENT
        // =========================================================

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {

            // -----------------------------------------------------
            // ۱. آیکون اصلی (Storefront) -> حرکت به سمت پایین (دل متن) و محو شدن
            // -----------------------------------------------------
            Icon(
                imageVector = Icons.Rounded.Storefront,
                contentDescription = "فروشگاه",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp)
                    .graphicsLayer {
                        translationY = -36.dp.toPx() + (36.dp.toPx() * easedProgress)

                        val iconScale = 1f - (0.70f * easedProgress)
                        scaleX = iconScale
                        scaleY = iconScale

                        alpha = 1f - easedProgress
                    }
            )

            // -----------------------------------------------------
            // ۲. متن اصلی -> حرکت پویا به سمت بالای صفحه (بالای شیت)
            // -----------------------------------------------------
            Text(
                text = "اتوسرویس ایرانیان",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 50.dp)
                    .graphicsLayer {
                        // محاسبه میزان بالا رفتن متناسب با ارتفاع صفحه
                        val targetTranslationY = height.toPx() * 0.46f
                        translationY = -targetTranslationY * easedProgress

                        scaleX = 1f - (0.08f * easedProgress)
                        scaleY = 1f - (0.08f * easedProgress)
                    }
            )

            // -----------------------------------------------------
            // ۳. زیرعنوان -> حرکت به سمت بالا (دل متن) و محو شدن
            // -----------------------------------------------------
            Text(
                text = "سیستم هوشمند مدیریت و حسابداری شما ✨",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme
                    .onPrimaryContainer
                    .copy(alpha = 0.75f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 95.dp)
                    .graphicsLayer {
                        translationY = -35.dp.toPx() * easedProgress

                        alpha = 1f - (easedProgress * 1.5f).coerceIn(0f, 1f)
                    }
            )
        }
    }
}

/**
 * آیکون‌های تزئینی اطراف Header
 */
@Composable
private fun FloatingHeaderIcon(
    icon: ImageVector,
    startX: Float,
    startY: Float,
    targetX: Float,
    targetY: Float,
    progress: Float,
    containerWidth: Dp,
    containerHeight: Dp,
    size: Dp,
    rotation: Float
) {
    val easedProgress = progress * progress * (3f - 2f * progress)

    val x = startX + (targetX - startX) * easedProgress
    val y = startY + (targetY - startY) * easedProgress

    val fadeProgress = ((easedProgress - 0.25f) / 0.75f).coerceIn(0f, 1f)
    val alpha = 1f - fadeProgress
    val scale = 1f - (0.55f * easedProgress)

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary.copy(
            alpha = alpha * 0.70f
        ),
        modifier = Modifier
            .size(size)
            .offset(
                x = containerWidth * x - size / 2,
                y = containerHeight * y - size / 2
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                rotationZ = rotation * (1f - easedProgress)
            }
    )
}