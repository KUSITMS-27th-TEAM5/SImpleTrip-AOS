package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpSuccessActivity : AppCompatActivity() {

    val finishButton :Button = findViewById(R.id.btn_signup_success)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_success)


        finishButton.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }





}