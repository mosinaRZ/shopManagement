package ir.hamedan.shopmanagement.core.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DoubleBackToExitHandler(
    message: String = "برای خروج، دوباره دکمه بازگشت را لمس کنید"
) {
    val context = LocalContext.current
    val activity = context as? Activity
    var backPressedOnce by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    BackHandler {
        if (backPressedOnce) {
            activity?.finish()
        } else {
            backPressedOnce = true
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            scope.launch {
                delay(2000L)
                backPressedOnce = false
            }
        }
    }
}