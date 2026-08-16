package ir.hamedan.shopmanagement.core.security

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * نگه‌داری شناسه‌ی کاربر لاگین‌شده در DataStore.
 * DataStore<Preferences> باید در AppModule با Hilt provide شود:
 *   @Provides @Singleton
 *   fun provideDataStore(@ApplicationContext ctx: Context): DataStore<Preferences> =
 *       PreferenceDataStoreFactory.create { ctx.preferencesDataStoreFile("session") }
 */
@Singleton
class SessionManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val currentUserIdKey = longPreferencesKey("current_user_id")

    val currentUserId: Flow<Long?> = dataStore.data.map { prefs ->
        prefs[currentUserIdKey]?.takeIf { it > 0 }
    }

    suspend fun setCurrentUserId(userId: Long) {
        dataStore.edit { it[currentUserIdKey] = userId }
    }

    suspend fun clearSession() {
        dataStore.edit { it.remove(currentUserIdKey) }
    }
}