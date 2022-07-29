package com.example.kotlintopmovies2.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUserData @Inject constructor(@ApplicationContext val context: Context) {

    companion object{
        private const val USER_INFO_DATASTORE = "user_info_datastore"
        private const val USER_TOKEN = "user_token"

        private val Context.dataStor:DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(USER_INFO_DATASTORE)
        val userToken = stringPreferencesKey(USER_TOKEN)
    }

    suspend fun saveUserToken(token:String) {
        context.dataStor.edit {
            it[userToken] = token
        }
    }
        fun getUserToken()=context.dataStor.data.map {
            it[userToken].orEmpty()
        }
    }

