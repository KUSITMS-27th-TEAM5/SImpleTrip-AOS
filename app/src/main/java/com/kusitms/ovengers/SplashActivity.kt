package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Splash 지연 시간 발생
        Handler(Looper.getMainLooper()).postDelayed({
            val intent= Intent( this,MainActivity::class.java)
            startActivity(intent)
            finish()
            // 2초
        }, 2000)
    }
} // 커밋용