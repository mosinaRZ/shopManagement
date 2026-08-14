package ir.hamedan.shopmanagement.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.components.BottomNavBar
import ir.hamedan.shopmanagement.feature.home.sections.DashboardSection
import ir.hamedan.shopmanagement.feature.home.sections.ProfitabilitySection
import ir.hamedan.shopmanagement.feature.home.sections.SmartManagementSection

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { BottomNavBar() },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            DashboardSection()

            ProfitabilitySection()

            SmartManagementSection()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}