package ir.hamedan.shopmanagement.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.hamedan.shopmanagement.core.navigation.AppNavGraph
import ir.hamedan.shopmanagement.core.navigation.NavigationManager
import ir.hamedan.shopmanagement.core.navigation.Routes
import ir.hamedan.shopmanagement.core.ui.components.BottomNavBar
import kotlinx.coroutines.launch

/**
 * نقطه‌ی ورود ناوبری اپ. اینجا Scaffold با BottomNavBar رو نگه می‌داریم
 * و AppNavGraph مسیر واقعی رو رندر می‌کنه. دیگه هیچ Routes یا NavHost
 * تکراری اینجا وجود نداره - تک منبع حقیقت همون core.navigation.Routes است.
 *
 * کپسول نئومورفیسم بالای هر ۵ صفحه‌ی اصلی (topBar) با عنوان همون صفحه
 * نمایش داده می‌شه - چون اینجا نصب شده، نیازی نیست هر Screen جداگانه
 * پیاده‌سازیش کنه؛ روی هر مسیر داخل bottomNavRoutes خودکار ظاهر می‌شه.
 */
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onItemClick = { route ->
                    if (route != currentRoute) {
                        scope.launch {
                            NavigationManager.navigateTo(
                                route = route,
                                popUpToRoute = Routes.Home.route,
                                inclusive = false
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}