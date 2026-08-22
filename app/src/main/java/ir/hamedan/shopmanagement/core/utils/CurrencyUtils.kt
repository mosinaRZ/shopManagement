package ir.hamedan.shopmanagement.core.utils

import java.text.DecimalFormat

object CurrencyUtils {
    private val formatter = DecimalFormat("#,###")

    fun formatPrice(amount: Double, suffix: String = "تومان"): String {
        return "${formatter.format(amount)} $suffix"
    }

    fun formatNumber(amount: Number): String {
        return formatter.format(amount)
    }
}