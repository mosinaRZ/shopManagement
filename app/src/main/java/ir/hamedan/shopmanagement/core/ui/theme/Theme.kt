package ir.hamedan.shopmanagement.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(

    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    // رنگ‌های ثانویه هماهنگ با تم نئومورفیسم (آبی/خاکستری ملایم)
    secondary = Color(0xFF4A6572),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD2E4EE),
    onSecondaryContainer = Color(0xFF0C1F28),

    tertiary = Color(0xFF5A5C7A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE0E0FF),
    onTertiaryContainer = Color(0xFF16172E),

    background = Background,
    onBackground = OnBackground,

    surface = Surface,
    onSurface = OnSurface,

    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,

    // رنگ‌های حاشیه برای مواقعی که از کارت‌های نئومورفیسم استفاده نمی‌کنید
    outline = Color(0xFF909A9E),
    outlineVariant = Color(0xFFC4D0DF),

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer
)

@Composable
fun ShopManagementTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // یکپارچه کردن رنگ استاتوس‌بار و نویگیشن‌بار با پس‌زمینه برای ظاهر یکدست و مینیمال
            window.statusBarColor = Background.toArgb()
            window.navigationBarColor = Background.toArgb()

            // تیره کردن آیکون‌های استاتوس‌بار چون پس‌زمینه ما روشن است
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}