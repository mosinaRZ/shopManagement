package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.components.NeumorphicCard
import ir.hamedan.shopmanagement.core.ui.theme.Warning as WarningColor

@Composable
fun SmartManagementSection() {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(bottom = 16.dp)) {
        Text(
            text = "مدیریت هوشمند",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        NeumorphicCard(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "موجودی ۳ کالا رو به اتمام است",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "سررسید ۲ چک در این هفته",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}