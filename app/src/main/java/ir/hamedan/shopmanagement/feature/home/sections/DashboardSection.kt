package ir.hamedan.shopmanagement.feature.home.sections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.hamedan.shopmanagement.core.ui.components.NeumorphicCard

@Composable
fun DashboardSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "داشبورد مدیریتی",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            DashboardItem(title = "کل فروش", value = "۲۵,۰۰۰,۰۰۰ ₸", modifier = Modifier.weight(1f))
            DashboardItem(title = "کل خرید", value = "۱۲,۵۰۰,۰۰۰ ₸", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            DashboardItem(title = "تراکنش‌ها", value = "۱۴۲", modifier = Modifier.weight(1f))
            DashboardItem(title = "انبار", value = "۱۵۰,۰۰۰,۰۰۰ ₸", modifier = Modifier.weight(1f))
        }
    }
}

// اصلاح DashboardItem
@Composable
fun DashboardItem(title: String, value: String, modifier: Modifier = Modifier) {
    NeumorphicCard(modifier = modifier.fillMaxWidth()) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1, // جلوگیری از شکسته شدن مبلغ
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}