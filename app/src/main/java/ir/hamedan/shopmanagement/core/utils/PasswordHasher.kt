package ir.hamedan.shopmanagement.core.utils

import java.security.MessageDigest

object PasswordHasher {

    /**
     * هش کردن امن کلمه عبور با الگوریتم SHA-256
     */
    fun hash(password: String): String {
        val bytes = password.toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    /**
     * بررسی تطابق پسورد ورودی با هش ذخیره‌شده
     */
    fun verify(password: String, expectedHash: String): Boolean {
        return hash(password).equals(expectedHash, ignoreCase = true)
    }
}