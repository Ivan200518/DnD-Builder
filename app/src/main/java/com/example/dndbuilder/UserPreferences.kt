import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Создание DataStore
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val SIGNED_IN = booleanPreferencesKey("signed_in")
    }

    val isSignedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SIGNED_IN] ?: false
        }

    suspend fun setSignedIn(signedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SIGNED_IN] = signedIn
        }
    }
}

