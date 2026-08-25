package ir.hamedan.shopmanagement.core.navigation

sealed class Routes(val route: String, val title: String) {
    object Splash : Routes("splash", "شروع")
    object Login : Routes("login", "ورود به حساب")
    object Home : Routes("home", "خانه")
    object Customers : Routes("customers", "مشتریان")
    object AddTransaction : Routes("add_transaction", "صدور فاکتور")
    object Reports : Routes("reports", "امور مالی")
    object Inventory : Routes("inventory", "انبار و کالاها")
    object Employees : Routes("employees", "پرسنل و حقوق")
    object Purchases : Routes("purchases", "خرید و فاکتور")
    object Settings : Routes("settings", "تنظیمات")
    object Profile : Routes("profile", "پروفایل کاربری")

    companion object {
        val bottomNavRoutes = listOf(Home, Customers, AddTransaction, Reports, Inventory)
        fun titleOf(route: String?): String =
            bottomNavRoutes.firstOrNull { it.route == route }?.title ?: Home.title
    }
}