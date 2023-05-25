package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kusitms.ovengers.databinding.ActivityCarrierInfoBinding
import com.kusitms.ovengers.databinding.ActivityTicketDetailBinding

class TicketDetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityTicketDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTicketDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.ticketName.setText(intent.getStringExtra("ticketName"))
        binding.ticketUrl.setImageResource(R.drawable.dafaultticket)

        binding.backBotton.setOnClickListener{

            val intent = Intent(this, CarrierInfoActivity::class.java )
            startActivity(intent)

        }
    }
}