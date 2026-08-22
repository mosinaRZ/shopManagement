package ir.hamedan.shopmanagement.core.navigation

sealed class Routes(val route: String, val title: String) {
    object Home : Routes("home", "خانه")
    object Customers : Routes("customers", "مشتریان")
    object AddTransaction : Routes("add_transaction", "صدور فاکتور")
    object Reports : Routes("reports", "امور مالی")
    object Inventory : Routes("inventory", "انبار و کالاها")

    // میانبرهای تکمیلی
    object Employees : Routes("employees", "پرسنل و حقوق")
    object Purchases : Routes("purchases", "خرید و فاکتور")
    object Settings : Routes("settings", "تنظیمات")
    object Login : Routes("login", "ورود / حساب")

    companion object {
        val bottomNavRoutes = listOf(Home, Customers, AddTransaction, Reports, Inventory)
        fun titleOf(route: String?): String =
            bottomNavRoutes.firstOrNull { it.route == route }?.title ?: Home.title
    }
}