package com.kusitms.ovengers

import android.app.Application

class GlobalApplication : Application() {
    companion object {
        lateinit var google : String
    }
}