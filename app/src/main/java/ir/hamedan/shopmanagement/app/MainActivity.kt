package ir.hamedan.shopmanagement.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import dagger.hilt.android.AndroidEntryPoint
import ir.hamedan.shopmanagement.core.ui.theme.ShopManagementTheme

/**
 * @AndroidEntryPoint لازمه چون:
 *  - از hiltViewModel() داخل Compose Navigation برای گرفتن ViewModel ها استفاده می‌کنیم
 *  - هر وابستگی‌ای که مستقیماً داخل Activity نیاز به inject شدن داشته باشه
 * بدون این annotation، Hilt نمی‌تونه به این Activity متصل بشه و در runtime کرش می‌کنه.
 *
 * LocalLayoutDirection رو صریحاً روی Rtl ست کردیم تا کل اپ - حتی اگه زبان
 * سیستم دستگاه فارسی/عربی نباشه - همیشه راست‌چین رندر بشه. Row/Column و
 * Arrangement.Start/End خودکار با همین جهت هماهنگ می‌شن.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopManagementTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    AppRoot()
                }
            }
        }
    }
}