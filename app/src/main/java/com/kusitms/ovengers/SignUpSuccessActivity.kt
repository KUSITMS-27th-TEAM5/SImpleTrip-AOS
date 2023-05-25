package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kusitms.ovengers.databinding.ActivitySignUpSuccessBinding
import com.kusitms.ovengers.databinding.ActivityTicketDetailBinding

class SignUpSuccessActivity : AppCompatActivity() {


    lateinit var binding : ActivitySignUpSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_success)

        binding = ActivitySignUpSuccessBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignupSuccess.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }
    }





} // 커밋용