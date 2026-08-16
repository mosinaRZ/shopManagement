package ir.hamedan.shopmanagement.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.hamedan.shopmanagement.core.navigation.AppNavGraph
import ir.hamedan.shopmanagement.core.navigation.NavigationManager
import ir.hamedan.shopmanagement.core.navigation.Routes
import ir.hamedan.shopmanagement.core.ui.components.BottomNavBar
import kotlinx.coroutines.launch

/**
 * نقطه‌ی ورود ناوبری اپ. Scaffold با BottomNavBar (کپسول نئومورفیسم +
 * ایندیکاتور liquid glass) رو نگه می‌داره و AppNavGraph مسیر واقعی رو
 * رندر می‌کنه. تک منبع حقیقت مسیرها همون core.navigation.Routes است.
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