package co.kr.soptandroidseminar.data.local

import android.content.Context
import android.content.SharedPreferences

object SharedPreference {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val USER_ID = "USER_ID"
    private const val USER_EMAIL = "USER_EMAIL"

    fun getAutoLogin(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(AUTO_LOGIN, false)
    }

    fun getUserId(context: Context): String? {
        return getSharedPreference(context).getString(USER_ID, "")
    }

    fun getUserEmail(context: Context): String? {
        return getSharedPreference(context).getString(USER_EMAIL, "")
    }

    fun setAutoLogin(context: Context, value: Boolean, userId: String, userEmail: String) {
        getSharedPreference(context).edit()
            .putBoolean(AUTO_LOGIN, value)
            .putString(USER_ID, userId)
            .putString(USER_EMAIL, userEmail)
            .apply()
    }

    fun removeAutoLogin(context: Context) {
        getSharedPreference(context).edit()
            .remove(AUTO_LOGIN)
            .apply()
    }

    fun clearAutoLogin(context: Context) {
        getSharedPreference(context).edit()
            .clear()
            .apply()
    }

    fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }
}