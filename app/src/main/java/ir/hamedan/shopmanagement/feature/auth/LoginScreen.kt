package ir.hamedan.shopmanagement.feature.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.core.ui.components.DoubleBackToExitHandler
import ir.hamedan.shopmanagement.feature.auth.sections.LoginFormSection

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    // جلوگیری از خروج ناخواسته با دکمه بک
    DoubleBackToExitHandler()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "نرم‌افزار مدیریت فروشگاه",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
            )

            LoginFormSection(
                onLoginClick = { _, _ ->
                    // ورود مستقیم و بدون شرط به صفحه خانه
                    onLoginSuccess()
                }
            )
        }
    }
}