package ir.hamedan.shopmanagement.domain.repository

import ir.hamedan.shopmanagement.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    /** جریان کاربر لاگین‌شده‌ی فعلی؛ null یعنی هیچ‌کس لاگین نیست */
    val currentUser: Flow<User?>

    /** @throws AppException.ValidationException در صورت نامعتبر بودن نام‌کاربری/رمز */
    suspend fun login(username: String, password: String): User

    suspend fun logout()

    suspend fun register(username: String, password: String, fullName: String): Long

    suspend fun isLoggedIn(): Boolean
}