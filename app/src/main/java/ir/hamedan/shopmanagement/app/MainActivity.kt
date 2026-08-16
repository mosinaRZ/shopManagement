package ir.hamedan.shopmanagement.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ir.hamedan.shopmanagement.core.ui.theme.ShopManagementTheme

/**
 * @AndroidEntryPoint لازمه چون:
 *  - از hiltViewModel() داخل Compose Navigation برای گرفتن ViewModel ها استفاده می‌کنیم
 *  - هر وابستگی‌ای که مستقیماً داخل Activity نیاز به inject شدن داشته باشه
 * بدون این annotation، Hilt نمی‌تونه به این Activity متصل بشه و در runtime کرش می‌کنه.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopManagementTheme {
                AppRoot()
            }
        }
    }
}