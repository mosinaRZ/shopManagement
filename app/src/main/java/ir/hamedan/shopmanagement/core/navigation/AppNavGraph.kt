package ir.hamedan.shopmanagement.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.hamedan.shopmanagement.feature.auth.LoginScreen
import ir.hamedan.shopmanagement.feature.customers.CustomerScreen
import ir.hamedan.shopmanagement.feature.employees.EmployeeScreen
import ir.hamedan.shopmanagement.feature.finance.FinanceScreen
import ir.hamedan.shopmanagement.feature.home.HomeScreen
import ir.hamedan.shopmanagement.feature.products.ProductScreen
import ir.hamedan.shopmanagement.feature.purchases.PurchaseScreen
import ir.hamedan.shopmanagement.feature.sales.SalesScreen
import ir.hamedan.shopmanagement.feature.settings.SettingsScreen

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
        composable(Routes.Home.route) { HomeScreen() }
        composable(Routes.Customers.route) { CustomerScreen() }
        composable(Routes.AddTransaction.route) { SalesScreen() }
        composable(Routes.Reports.route) { FinanceScreen() }
        composable(Routes.Inventory.route) { ProductScreen() }
        composable(Routes.Employees.route) { EmployeeScreen() }
        composable(Routes.Purchases.route) { PurchaseScreen() }
        composable(Routes.Settings.route) { SettingsScreen() }
        composable(Routes.Login.route) { LoginScreen() }
    }
}