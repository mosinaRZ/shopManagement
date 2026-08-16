package ir.hamedan.shopmanagement.core.security

import java.security.MessageDigest

/**
 * هش ساده‌ی رمز عبور برای اپ آفلاین لوکال.
 * توجه: برای اپ‌هایی که سرور/بک‌اند دارند بهتره از bcrypt/Argon2 روی سرور استفاده شود؛
 * این پیاده‌سازی برای دیتابیس لوکال Room کافی و رایج است.
 */
object PasswordHasher {
    private const val SALT = "shopmanagement_v1_salt"

    fun hash(rawPassword: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest((SALT + rawPassword).toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }

    fun verify(rawPassword: String, hashed: String): Boolean = hash(rawPassword) == hashed
}