package ir.hamedan.shopmanagement.feature.auth.sections

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FloatingAuthIcon(
    icon: ImageVector,
    initialOffsetX: Dp,
    initialOffsetY: Dp,
    targetOffsetY: Dp,
    durationMillis: Int,
    size: Dp = 44.dp,
    tint: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
    bgColor: Color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f)
) {
    val infiniteTransition = rememberInfiniteTransition(label = "floating_auth_anim")
    val animatedOffsetY by infiniteTransition.animateValue(
        initialValue = initialOffsetY,
        targetValue = targetOffsetY,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset_y"
    )

    Box(
        modifier = Modifier
            .offset(x = initialOffsetX, y = animatedOffsetY)
            .size(size)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size * 0.55f)
        )
    }
}