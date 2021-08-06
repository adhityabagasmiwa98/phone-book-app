package com.adhityabagasmiwa.phonebook.sessions

import android.content.SharedPreferences
import androidx.core.content.edit

class LocalSession(private val sharedPreferences: SharedPreferences) {
    private val tokenKey = "token"

    fun getToken() = sharedPreferences.getString(tokenKey, "") ?: ""
    fun saveToken(value: String) = sharedPreferences.edit { putString(tokenKey, "BEARER $value") }
    fun removeToken() = sharedPreferences.edit { remove(tokenKey) }
}