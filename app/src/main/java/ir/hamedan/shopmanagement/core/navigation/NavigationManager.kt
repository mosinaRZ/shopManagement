package ir.hamedan.shopmanagement.core.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

sealed class NavigationCommand {
    data class NavigateTo(
        val route: String,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false
    ) : NavigationCommand()

    object NavigateBack : NavigationCommand()
}

object NavigationManager {

    private val _commands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = 1)
    val commands: SharedFlow<NavigationCommand> = _commands

    suspend fun navigateTo(route: String, popUpToRoute: String? = null, inclusive: Boolean = false) {
        _commands.emit(NavigationCommand.NavigateTo(route, popUpToRoute, inclusive))
    }

    suspend fun navigateBack() {
        _commands.emit(NavigationCommand.NavigateBack)
    }
}