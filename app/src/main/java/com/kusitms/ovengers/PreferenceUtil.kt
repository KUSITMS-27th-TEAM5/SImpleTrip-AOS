package com.kusitms.ovengers

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context)// 커밋용
{
    private val prefs: SharedPreferences = context.getSharedPreferences("SharedPreferences", 0)

    fun getString(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String)
    {
        prefs.edit().putString(key, str).apply()
    }

}