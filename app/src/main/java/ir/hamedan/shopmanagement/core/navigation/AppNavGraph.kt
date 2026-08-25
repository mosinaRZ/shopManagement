package ir.hamedan.shopmanagement.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import ir.hamedan.shopmanagement.feature.profile.ProfileScreen
import ir.hamedan.shopmanagement.feature.purchases.PurchaseScreen
import ir.hamedan.shopmanagement.feature.sales.SalesScreen
import ir.hamedan.shopmanagement.feature.settings.SettingsScreen
import ir.hamedan.shopmanagement.feature.splash.SplashScreen

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
        startDestination = Routes.Splash.route,
        modifier = modifier.fillMaxSize(),
        // انیمیشن اسلاید ورود صفحه جدید (از سمت راست به چپ همراه با FadeIn)
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300))
        },
        // انیمیشن خروج صفحه فعلی (اسلاید به چپ همراه با FadeOut)
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(300))
        },
        // انیمیشن بازگشت (Pop Enter - اسلاید معکوس از چپ به راست)
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(300))
        },
        // انیمیشن خروج حین بازگشت (Pop Exit - اسلاید به سمت راست)
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen(
                onNavigateBack = { navController.popBackStack() },
                onLogoutSuccess = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home.route) { HomeScreen() }
        composable(Routes.Customers.route) { CustomerScreen() }
        composable(Routes.AddTransaction.route) { SalesScreen() }
        composable(Routes.Reports.route) { FinanceScreen() }
        composable(Routes.Inventory.route) { ProductScreen() }
        composable(Routes.Employees.route) { EmployeeScreen() }
        composable(Routes.Purchases.route) { PurchaseScreen() }
        composable(Routes.Settings.route) { SettingsScreen() }
    }
}