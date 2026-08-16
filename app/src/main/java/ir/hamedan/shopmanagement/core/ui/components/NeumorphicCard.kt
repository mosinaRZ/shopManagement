package ir.hamedan.shopmanagement.core.ui.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    content: @Composable () -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.surface
    // استفاده از رنگ‌های سخت‌افزاری نئومورفیسم
    val lightShadow = Color(0xFFFFFFFF).copy(alpha = 0.9f)
    val darkShadow = Color(0xFFC4D0DF).copy(alpha = 0.8f)

    Box(
        modifier = modifier
            .drawBehind {
                val cornerRadiusPx = CornerRadius(cornerRadius.toPx())
                val offsetPx = 6.dp.toPx()
                val blurPx = 12.dp.toPx()

                drawIntoCanvas { canvas ->
                    val paint = Paint().asFrameworkPaint().apply {
                        isAntiAlias = true
                        maskFilter = BlurMaskFilter(blurPx, BlurMaskFilter.Blur.NORMAL)
                    }

                    // Dark Shadow (Bottom-Right)
                    paint.color = darkShadow.toArgb()
                    canvas.nativeCanvas.drawRoundRect(
                        offsetPx, offsetPx,
                        size.width + offsetPx, size.height + offsetPx,
                        cornerRadiusPx.x, cornerRadiusPx.y,
                        paint
                    )

                    // Light Shadow (Top-Left)
                    paint.color = lightShadow.toArgb()
                    canvas.nativeCanvas.drawRoundRect(
                        -offsetPx, -offsetPx,
                        size.width - offsetPx, size.height - offsetPx,
                        cornerRadiusPx.x, cornerRadiusPx.y,
                        paint
                    )
                }
            }
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .padding(20.dp) // پدینگ بیشتر برای حس مینیمال
    ) {
        content()
    }
}