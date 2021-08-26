package app.tendo.data.local.dataStore

import android.content.Context
import app.tendo.Config.TENDO_PREF_DATA_STORE_NAME
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import app.tendo.Config
import app.tendo.Config.USER_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by goodlife on 26,August,2021
 */
// At the top level of your kotlin file:


class DataManager(context: Context) {

    private val dataStore = context.createDataStore(TENDO_PREF_DATA_STORE_NAME)

    val USER_NAME = preferencesKey<String>(Config.USER_NAME)
    val USER_NO = preferencesKey<Int>(Config.USER_NO)


    suspend fun storeData(name:String, no:Int){
        dataStore.edit {
            it[USER_NAME] = name
            it[USER_NO] = no
        }
    }

    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME] ?: ""
    }


    val userNo: Flow<Int> = dataStore.data.map {
        it[USER_NO] ?: -1
    }

    suspend fun putString(key: String, value: String) {
        dataStore.edit {
            it[preferencesKey<String>(key)] = value
        }
    }

    suspend fun putInt(key: String, value: Int) {
        dataStore.edit {
            it[preferencesKey<Int>(key)] = value
        }
    }



}