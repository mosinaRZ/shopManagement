package ir.hamedan.shopmanagement.feature.auth

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.components.DoubleBackToExitHandler
import ir.hamedan.shopmanagement.feature.auth.sections.LoginFormSection

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    // جلوگیری از خروج ناخواسته با دکمه بک
    DoubleBackToExitHandler()

    // انیمیشن ورود ملایم برای هدر و پنل فرم (هم‌راستا با ریتم انیمیشن HomeHeader)
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    val panelOffset by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 48.dp,
        animationSpec = tween(durationMillis = 550, easing = FastOutSlowInEasing),
        label = "panelOffset"
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 450),
        label = "contentAlpha"
    )
    val logoScale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.6f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "logoScale"
    )

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val headerHeight = (screenHeight * 0.34f).coerceIn(200.dp, 300.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {

        // =========================================================
        // هدر برند — دقیقاً هم‌راستا با پس‌زمینه و آیکون‌های شناور HomeHeader
        // =========================================================
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        ) {
            val width = maxWidth
            val height = maxHeight

            FloatingAuthIcon(Icons.Rounded.ShoppingCart, 0.16f, 0.28f, width, height, 26.dp, -18f)
            FloatingAuthIcon(Icons.Rounded.Sell, 0.83f, 0.24f, width, height, 20.dp, 16f)
            FloatingAuthIcon(Icons.Rounded.AccountBalanceWallet, 0.86f, 0.64f, width, height, 24.dp, 22f)
            FloatingAuthIcon(Icons.Rounded.Money, 0.14f, 0.68f, width, height, 19.dp, -22f)

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .graphicsLayer {
                        alpha = contentAlpha
                        scaleX = logoScale
                        scaleY = logoScale
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "نرم‌افزار مدیریت فروشگاه",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "سیستم یکپارچه مدیریت و حسابداری",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // =========================================================
        // پنل فرم ورود — همان شکل sheet با گوشه‌های ۳۲ دی‌پی مثل HomeScreen
        // =========================================================
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .graphicsLayer {
                    translationY = panelOffset.toPx()
                    alpha = contentAlpha
                },
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 0.dp,
            tonalElevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                LoginFormSection(
                    modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
                    onLoginClick = { _, _ ->
                        // ورود مستقیم و بدون شرط به صفحه خانه
                        onLoginSuccess()
                    }
                )
            }
        }
    }
}

/**
 * آیکون تزئینی شناور در هدر ورود، هم‌راستا با FloatingHeaderIcon در HomeHeader
 */
@Composable
private fun FloatingAuthIcon(
    icon: ImageVector,
    xFraction: Float,
    yFraction: Float,
    containerWidth: Dp,
    containerHeight: Dp,
    size: Dp,
    rotation: Float
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
        modifier = Modifier
            .size(size)
            .offset(
                x = containerWidth * xFraction - size / 2,
                y = containerHeight * yFraction - size / 2
            )
            .graphicsLayer { rotationZ = rotation }
    )
}