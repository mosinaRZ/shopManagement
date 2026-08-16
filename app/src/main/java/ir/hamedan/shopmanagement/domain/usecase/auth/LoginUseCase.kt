package ir.hamedan.shopmanagement.domain.usecase.auth

import ir.hamedan.shopmanagement.domain.model.User
import ir.hamedan.shopmanagement.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): User {
        return repository.login(username, password)
    }
}