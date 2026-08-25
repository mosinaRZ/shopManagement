package ir.hamedan.shopmanagement.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.hamedan.shopmanagement.core.navigation.AppNavGraph
import ir.hamedan.shopmanagement.core.navigation.NavigationManager
import ir.hamedan.shopmanagement.core.navigation.Routes
import ir.hamedan.shopmanagement.core.ui.components.BottomNavBar
import ir.hamedan.shopmanagement.core.ui.components.QuickAddOverlay
import kotlinx.coroutines.launch

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    var isAddMenuExpanded by remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    // باتم‌بار فقط و فقط در ۵ صفحه اصلی که در لیست bottomNavRoutes هستند نمایش داده می‌شود
    val shouldShowBottomBar = Routes.bottomNavRoutes.any { it.route == currentRoute }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            bottomBar = {
                if (shouldShowBottomBar) {
                    BottomNavBar(
                        currentRoute = currentRoute,
                        isAddMenuExpanded = isAddMenuExpanded,
                        onAddClick = { isAddMenuExpanded = !isAddMenuExpanded },
                        onItemClick = { route ->
                            isAddMenuExpanded = false
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
            }
        ) { innerPadding ->
            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(
                    if (shouldShowBottomBar) innerPadding else PaddingValues()
                )
            )
        }

        // لایه اورلی افزودن سریع فقط وقتی که در صفحات باتم‌بار هستیم
        if (shouldShowBottomBar) {
            QuickAddOverlay(
                expanded = isAddMenuExpanded,
                onDismiss = { isAddMenuExpanded = false },
                onActionClick = { route ->
                    isAddMenuExpanded = false
                    scope.launch {
                        NavigationManager.navigateTo(route)
                    }
                }
            )
        }
    }
}