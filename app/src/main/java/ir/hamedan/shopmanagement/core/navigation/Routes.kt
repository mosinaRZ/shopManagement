package ir.hamedan.shopmanagement.core.navigation

sealed class Routes(val route: String, val title: String) {
    object Home : Routes("home", "خانه")
    object Customers : Routes("customers", "مشتریان")
    object AddTransaction : Routes("add_transaction", "افزودن تراکنش")
    object Reports : Routes("reports", "آمار")
    object Inventory : Routes("inventory", "انبار")

    companion object {
        val bottomNavRoutes = listOf(Home, Customers, AddTransaction, Reports, Inventory)

        fun titleOf(route: String?): String =
            bottomNavRoutes.firstOrNull { it.route == route }?.title ?: Home.title
    }
}