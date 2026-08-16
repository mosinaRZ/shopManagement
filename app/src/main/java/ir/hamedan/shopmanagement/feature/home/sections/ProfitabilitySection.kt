package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.components.NeumorphicCard
import ir.hamedan.shopmanagement.core.ui.theme.Info
import ir.hamedan.shopmanagement.core.ui.theme.Success

@Composable
fun ProfitabilitySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "تحلیل سودآوری 💰",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        NeumorphicCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "سود ناخالص",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "۱۲,۵۰۰,۰۰۰ ₸",
                        style = MaterialTheme.typography.titleMedium,
                        color = Success
                    )
                }
                Column {
                    Text(
                        text = "سود خالص",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "۹,۲۰۰,۰۰۰ ₸",
                        style = MaterialTheme.typography.titleMedium,
                        color = Info
                    )
                }
            }
        }
    }
}