package ir.hamedan.shopmanagement.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val CURRENCY_KEY = stringPreferencesKey("app_currency")
    }

    val currencyFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[CURRENCY_KEY] ?: "تومان"
    }

    suspend fun setCurrency(currency: String) {
        dataStore.edit { preferences ->
            preferences[CURRENCY_KEY] = currency
        }
    }
}