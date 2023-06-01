package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpSuccessActivity : AppCompatActivity() {// 커밋용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_success)

        val finishButton = findViewById<Button>(R.id.btn_signup_success)


        finishButton.setOnClickListener {
            val intent = Intent(this, BoardingActivity_1::class.java)
            finish()
            startActivity(intent)
        }
    }

}