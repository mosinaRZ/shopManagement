package ir.hamedan.shopmanagement.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hamedan.shopmanagement.feature.settings.sections.DatabaseBackupSection
import ir.hamedan.shopmanagement.feature.settings.sections.ShopProfileSection

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    var shopName by remember { mutableStateOf("فروشگاه هایپرمارکت صداقت") }
    var ownerName by remember { mutableStateOf("محمد حسینی") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "تنظیمات برنامه",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        ShopProfileSection(
            shopName = shopName,
            ownerName = ownerName,
            currency = "تومان",
            onShopNameChange = { shopName = it },
            onOwnerNameChange = { ownerName = it }
        )

        DatabaseBackupSection(
            onBackupClick = { /* لاجیک بکاپ */ }
        )
    }
}