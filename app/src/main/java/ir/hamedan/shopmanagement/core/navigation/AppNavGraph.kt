package ir.hamedan.shopmanagement.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.hamedan.shopmanagement.feature.home.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        NavigationManager.commands.collect { command ->
            when (command) {
                is NavigationCommand.NavigateTo -> {
                    navController.navigate(command.route) {
                        command.popUpToRoute?.let { popRoute ->
                            popUpTo(popRoute) { inclusive = command.inclusive }
                        }
                        launchSingleTop = true
                    }
                }
                is NavigationCommand.NavigateBack -> navController.popBackStack()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Routes.Home.route) {
            HomeScreen()
        }
        composable(Routes.Customers.route) {
            // TODO: CustomersScreen()
        }
        composable(Routes.AddTransaction.route) {
            // TODO: AddTransactionScreen()
        }
        composable(Routes.Reports.route) {
            // TODO: ReportsScreen()
        }
        composable(Routes.Inventory.route) {
            // TODO: InventoryScreen()
        }
    }
}