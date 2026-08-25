package ir.hamedan.shopmanagement.core.utils

object ValidationUtils {

    /**
     * اعتبارسنجی شماره همراه ایران (فرمت‌های 0918..., +98918..., 0098918...)
     */
    fun isValidIranianPhone(phone: String): Boolean {
        val cleanPhone = phone.trim()
            .replace(" ", "")
            .replace("-", "")
            .replace("+98", "0")
            .replace("0098", "0")

        val regex = Regex("^09[0-9]{9}$")
        return regex.matches(cleanPhone)
    }

    /**
     * اعتبارسنجی قیمت و مبالغ مالی (باید عدد مثبت باشد)
     */
    fun isValidPrice(price: Double): Boolean = price >= 0.0

    /**
     * اعتبارسنجی موجودی انبار
     */
    fun isValidQuantity(quantity: Int): Boolean = quantity >= 0

    /**
     * اعتبارسنجی نام و عنوان
     */
    fun isValidTitle(text: String, minLength: Int = 2): Boolean = text.trim().length >= minLength
}