package com.conde.kun.fija.data.datasource

interface SettingsDataSource {
    fun getToken(): String?
    fun setToken(token: String?)
}