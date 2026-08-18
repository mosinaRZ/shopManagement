package ir.hamedan.shopmanagement.core.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.navigation.Routes

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(Routes.Home.route, "خانه", Icons.Default.Home),
    BottomNavItem(Routes.Customers.route, "مشتریان", Icons.Default.Person),
    BottomNavItem(Routes.AddTransaction.route, "افزودن", Icons.Default.Add),
    BottomNavItem(Routes.Reports.route, "آمار", Icons.Default.List),
    BottomNavItem(Routes.Inventory.route, "انبار", Icons.Default.ShoppingCart)
)

@Composable
fun BottomNavBar(
    currentRoute: String?,
    isAddMenuExpanded: Boolean = false,
    onAddClick: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val cornerRadius = 24.dp
    val fabSize = 55.dp
    val fabOffsetY = (-35).dp
    val notchGap = 5.dp

    val density = LocalDensity.current
    val capsuleShape = remember(density, cornerRadius, fabSize, fabOffsetY, notchGap) {
        notchedCapsuleShape(
            density = density,
            cornerRadius = cornerRadius,
            notchCenterFraction = 0.5f,
            notchCenterY = fabOffsetY + fabSize / 2,
            notchRadius = fabSize / 2 + notchGap
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, capsuleShape)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.15f),
                    capsuleShape
                )
                .clip(capsuleShape)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                bottomNavItems.forEach { item ->
                    if (item.route != Routes.AddTransaction.route) {
                        NavItemContent(
                            item = item,
                            selected = item.route == currentRoute,
                            onClick = { onItemClick(item.route) }
                        )
                    } else {
                        Box(modifier = Modifier.size(24.dp))
                    }
                }
            }
        }

        AddNavItem(
            isExpanded = isAddMenuExpanded,
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = fabOffsetY)
        )
    }
}

private fun notchedCapsuleShape(
    density: androidx.compose.ui.unit.Density,
    cornerRadius: Dp,
    notchCenterFraction: Float,
    notchCenterY: Dp,
    notchRadius: Dp
): Shape = GenericShape { size, _ ->
    val cornerPx = with(density) { cornerRadius.toPx() }
    val notchRadiusPx = with(density) { notchRadius.toPx() }
    val notchCenterYPx = with(density) { notchCenterY.toPx() }
    val notchCenterXPx = size.width * notchCenterFraction

    val basePath = Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                cornerRadius = CornerRadius(cornerPx, cornerPx)
            )
        )
    }
    val notchPath = Path().apply {
        addOval(
            Rect(
                center = Offset(notchCenterXPx, notchCenterYPx),
                radius = notchRadiusPx
            )
        )
    }

    this.op(basePath, notchPath, PathOperation.Difference)
}

@Composable
private fun AddNavItem(
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // چرخش ۴۵ درجه آیکون دکمه هنگام باز شدن
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 135f else 0f,
        animationSpec = spring(),
        label = "fabRotation"
    )

    Box(
        modifier = modifier
            .size(55.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "افزودن",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(35.dp)
                .graphicsLayer {
                    rotationZ = rotation
                }
        )
    }
}

@Composable
private fun NavItemContent(
    item: BottomNavItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        },
        label = "itemColor"
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.label,
            tint = contentColor,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = item.label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Normal,
            color = contentColor,
            maxLines = 1
        )
    }
}