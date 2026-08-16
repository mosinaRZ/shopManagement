package ir.hamedan.shopmanagement.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.feature.home.sections.DashboardSection
import ir.hamedan.shopmanagement.feature.home.sections.ProfitabilitySection
import ir.hamedan.shopmanagement.feature.home.sections.SmartManagementSection

@Composable
fun HomeSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        DashboardSection()
        ProfitabilitySection()
        SmartManagementSection()
    }
}