package com.conde.kun.fija.data.datasource.remote

import android.content.Context
import com.conde.kun.fija.data.datasource.SettingsDataSource

class LocalSettingsDataSource(context: Context) : SettingsDataSource {

    val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun setToken(token: String?) {
        val edit = sharedPreferences.edit()
        edit.putString("token", token)
        edit.commit()
    }

}