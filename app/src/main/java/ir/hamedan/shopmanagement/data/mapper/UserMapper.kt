package ir.hamedan.shopmanagement.data.mapper

import ir.hamedan.shopmanagement.data.local.entity.UserEntity
import ir.hamedan.shopmanagement.domain.model.User
import ir.hamedan.shopmanagement.domain.model.UserRole

fun UserEntity.toDomain(): User = User(
    id = id,
    username = username,
    fullName = fullName,
    role = runCatching { UserRole.valueOf(role) }.getOrDefault(UserRole.EMPLOYEE),
    isActive = isActive,
    createdAt = createdAt
)