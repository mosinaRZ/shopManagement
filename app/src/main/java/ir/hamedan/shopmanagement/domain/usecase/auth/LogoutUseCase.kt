package ir.hamedan.shopmanagement.domain.usecase.auth

import ir.hamedan.shopmanagement.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }
}