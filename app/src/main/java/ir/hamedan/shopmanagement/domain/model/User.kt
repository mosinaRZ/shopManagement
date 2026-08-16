package ir.hamedan.shopmanagement.domain.model

enum class UserRole {
    OWNER, EMPLOYEE
}

data class User(
    val id: Long = 0L,
    val username: String,
    val fullName: String,
    val role: UserRole = UserRole.EMPLOYEE,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)