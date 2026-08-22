package ir.hamedan.shopmanagement.core.utils

object ValidationUtils {
    fun isValidIranianPhone(phone: String): Boolean {
        val cleanPhone = phone.trim().replace(" ", "")
        val regex = Regex("^(09|\\+989)\\d{9}$")
        return cleanPhone.matches(regex)
    }

    fun isValidPrice(price: String): Boolean {
        val clean = price.replace(",", "").trim()
        val value = clean.toDoubleOrNull()
        return value != null && value >= 0
    }
}