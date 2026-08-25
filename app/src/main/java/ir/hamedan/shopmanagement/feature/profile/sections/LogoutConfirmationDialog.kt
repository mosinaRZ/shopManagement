package ir.hamedan.shopmanagement.feature.profile.sections

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LogoutConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmLogout: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(
                text = "خروج از حساب کاربری",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(
                text = "آیا مطمئن هستید که می‌خواهید از حساب خود خارج شوید؟ برای دسترسی مجدد باید کلمه عبور را وارد نمایید."
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirmLogout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("بله، خروج")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("انصراف")
            }
        }
    )
}