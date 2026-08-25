package ir.hamedan.shopmanagement.feature.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hamedan.shopmanagement.domain.model.User
import ir.hamedan.shopmanagement.domain.repository.AuthRepository
import ir.hamedan.shopmanagement.domain.usecase.auth.LogoutUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val currentUser: Flow<User?> = authRepository.currentUser

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            logoutUseCase()
            onSuccess()
        }
    }
}