package ir.hamedan.shopmanagement.feature.auth.sections

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class LoginButtonState {
    IDLE, LOADING, SUCCESS
}

@Composable
fun AnimatedLoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var buttonState by remember { mutableStateOf(LoginButtonState.IDLE) }
    val scope = rememberCoroutineScope()

    // انیمیشن تغییر عرض دکمه (از مستطیل به دایره لودینگ)
    val buttonWidth by animateDpAsState(
        targetValue = when (buttonState) {
            LoginButtonState.IDLE -> 320.dp
            LoginButtonState.LOADING, LoginButtonState.SUCCESS -> 56.dp
        },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "btn_width"
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (buttonState == LoginButtonState.SUCCESS) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy),
        label = "btn_scale"
    )

    // گرادینت متحرک دکمه
    val gradientColors = when (buttonState) {
        LoginButtonState.IDLE, LoginButtonState.LOADING -> listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.tertiary
        )
        LoginButtonState.SUCCESS -> listOf(
            Color(0xFF10B981),
            Color(0xFF059669)
        )
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .scale(scaleAnim)
                .width(buttonWidth)
                .height(56.dp)
                .shadow(
                    elevation = if (buttonState == LoginButtonState.IDLE) 8.dp else 4.dp,
                    shape = RoundedCornerShape(28.dp),
                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                )
                .clip(RoundedCornerShape(28.dp))
                .background(Brush.horizontalGradient(gradientColors))
                .clickable(
                    enabled = enabled && buttonState == LoginButtonState.IDLE,
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    buttonState = LoginButtonState.LOADING
                    scope.launch {
                        // شبیه‌سازی ورود هوشمند و سپس انتقال
                        delay(1200)
                        buttonState = LoginButtonState.SUCCESS
                        delay(600)
                        onClick()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = buttonState,
                transitionSpec = {
                    fadeIn(tween(300)) + scaleIn(tween(300)) togetherWith
                            fadeOut(tween(300)) + scaleOut(tween(300))
                },
                label = "btn_content"
            ) { state ->
                when (state) {
                    LoginButtonState.IDLE -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        ) {
                            Text(
                                text = "ورود به فروشگاه",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    LoginButtonState.LOADING -> {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    LoginButtonState.SUCCESS -> {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "موفق",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }
}