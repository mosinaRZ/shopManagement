package ir.hamedan.shopmanagement.data.repository

import ir.hamedan.shopmanagement.core.security.PasswordHasher
import ir.hamedan.shopmanagement.core.security.SessionManager
import ir.hamedan.shopmanagement.core.utils.AppException
import ir.hamedan.shopmanagement.data.local.dao.UserDao
import ir.hamedan.shopmanagement.data.local.entity.UserEntity
import ir.hamedan.shopmanagement.data.mapper.toDomain
import ir.hamedan.shopmanagement.domain.model.User
import ir.hamedan.shopmanagement.domain.model.UserRole
import ir.hamedan.shopmanagement.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionManager: SessionManager
) : AuthRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val currentUser: Flow<User?> =
        sessionManager.currentUserId.flatMapLatest { userId ->
            if (userId == null) {
                flowOf(null)
            } else {
                userDao.getByIdFlow(userId).map { it?.toDomain() }
            }
        }

    override suspend fun login(username: String, password: String): User {
        if (username.isBlank() || password.isBlank()) {
            throw AppException.ValidationException("ورود", "نام‌کاربری و رمز عبور نمی‌توانند خالی باشند")
        }
        val entity = userDao.getByUsername(username.trim())
            ?: throw AppException.ValidationException("ورود", "نام‌کاربری یا رمز عبور اشتباه است")

        if (!PasswordHasher.verify(password, entity.passwordHash)) {
            throw AppException.ValidationException("ورود", "نام‌کاربری یا رمز عبور اشتباه است")
        }
        sessionManager.setCurrentUserId(entity.id)
        return entity.toDomain()
    }

    override suspend fun logout() {
        sessionManager.clearSession()
    }

    override suspend fun register(username: String, password: String, fullName: String): Long {
        if (username.isBlank() || password.length < 4) {
            throw AppException.ValidationException("ثبت‌نام", "رمز عبور باید حداقل ۴ کاراکتر باشد")
        }
        val existing = userDao.getByUsername(username.trim())
        if (existing != null) {
            throw AppException.ValidationException("نام‌کاربری", "قبلاً استفاده شده است")
        }
        // اولین کاربر ثبت‌شده به‌صورت پیش‌فرض OWNER است
        val isFirstUser = userDao.getUserCount() == 0
        val entity = UserEntity(
            username = username.trim(),
            passwordHash = PasswordHasher.hash(password),
            fullName = fullName,
            role = if (isFirstUser) UserRole.OWNER.name else UserRole.EMPLOYEE.name,
            isActive = true,
            createdAt = System.currentTimeMillis()
        )
        return userDao.insert(entity)
    }

    override suspend fun isLoggedIn(): Boolean =
        sessionManager.currentUserId.first() != null
}