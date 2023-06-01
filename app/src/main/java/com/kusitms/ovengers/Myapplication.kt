package com.kusitms.ovengers

import android.app.Application

class MyApplication : Application()
{// 커밋용
    companion object
    {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate()
    {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}