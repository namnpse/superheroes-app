package com.namnp.heroes.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.namnp.heroes.domain.model.User
import com.namnp.heroes.domain.repository.DataStoreOperations
import com.namnp.heroes.util.Constants.PREFERENCES_KEY
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_AVATAR
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_BIO
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_EMAIL
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_ID
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_PHONE
import com.namnp.heroes.util.Constants.PREFERENCES_KEY_USERNAME
import com.namnp.heroes.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
        val idKey = stringPreferencesKey(name = PREFERENCES_KEY_ID)
        val usernameKey = stringPreferencesKey(name = PREFERENCES_KEY_USERNAME)
        val emailKey = stringPreferencesKey(name = PREFERENCES_KEY_EMAIL)
        val phoneKey = stringPreferencesKey(name = PREFERENCES_KEY_PHONE)
        val bioKey = stringPreferencesKey(name = PREFERENCES_KEY_BIO)
        val avatarKey = stringPreferencesKey(name = PREFERENCES_KEY_AVATAR)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override suspend fun saveUserInfo(user: User): Unit = withContext(Dispatchers.IO) {
        dataStore.edit { preferences ->
            if(!user.id.isNullOrEmpty())
                preferences[PreferencesKey.idKey] = user.id
            if(!user.nickName.isNullOrEmpty())
                preferences[PreferencesKey.usernameKey] = user.nickName
            if(!user.email.isNullOrEmpty())
                preferences[PreferencesKey.emailKey] = user.email
            if(!user.phone.isNullOrEmpty())
                preferences[PreferencesKey.phoneKey] = user.phone
            if(!user.bio.isNullOrEmpty())
                preferences[PreferencesKey.bioKey] = user.bio
            if(!user.photoUrl.isNullOrEmpty())
                preferences[PreferencesKey.avatarKey] = user.photoUrl
        }
    }

    override fun readUserInfo(): Flow<User> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val id = preferences[PreferencesKey.idKey] ?: ""
                val nickName = preferences[PreferencesKey.usernameKey] ?: ""
                val email = preferences[PreferencesKey.emailKey] ?: ""
                val phone = preferences[PreferencesKey.phoneKey] ?: ""
                val bio = preferences[PreferencesKey.bioKey] ?: ""
                val photoUrl = preferences[PreferencesKey.avatarKey] ?: ""
                User(
                    id = id,
                    nickName = nickName,
                    email = email,
                    phone = phone,
                    bio = bio,
                    photoUrl = photoUrl,
                )
            }
    }

    override suspend fun clearUserInfo(): Unit = withContext(Dispatchers.IO) {
        dataStore.edit {
            it.clear()
        }
    }
}