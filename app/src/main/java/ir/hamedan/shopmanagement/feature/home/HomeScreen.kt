package ir.hamedan.shopmanagement.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.feature.home.sections.HomeHeader
import ir.hamedan.shopmanagement.feature.home.sections.HomeSheetContent
import ir.hamedan.shopmanagement.feature.home.sections.NotificationsBottomSheet
import ir.hamedan.shopmanagement.feature.home.sections.sampleNotifications

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val density = LocalDensity.current

    val peekHeight = (screenHeight * 0.38f).coerceIn(220.dp, 340.dp)

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val partialOffset = with(density) { (screenHeight - peekHeight).toPx() }
    val currentOffset = try {
        sheetState.requireOffset()
    } catch (_: IllegalStateException) {
        partialOffset
    }
    val sheetProgress = if (partialOffset > 0f) {
        ((partialOffset - currentOffset) / partialOffset).coerceIn(0f, 1f)
    } else 0f

    // ---- state باتم‌شیت اعلان‌ها ----
    var showNotifications by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(sampleNotifications) }
    val unreadCount = notifications.count { !it.isRead }

    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = peekHeight,
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        sheetShadowElevation = 0.dp,
        sheetTonalElevation = 0.dp,
        sheetDragHandle = null,
        sheetContent = { HomeSheetContent() },
        content = { innerPadding ->
            HomeHeader(
                modifier = Modifier.padding(innerPadding),
                sheetProgress = sheetProgress,
                onNotificationsClick = { showNotifications = true }
            )
        }
    )

    if (showNotifications) {
        NotificationsBottomSheet(
            notifications = notifications,
            unreadCount = unreadCount,
            onDismissRequest = { showNotifications = false },
            onMarkAsRead = { id ->
                notifications = notifications.map {
                    if (it.id == id) it.copy(isRead = true) else it
                }
            },
            onMarkAllAsRead = {
                notifications = notifications.map { it.copy(isRead = true) }
            }
        )
    }
}