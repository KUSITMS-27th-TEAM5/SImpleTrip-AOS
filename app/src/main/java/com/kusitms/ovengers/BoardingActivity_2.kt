package com.kusitms.ovengers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class BoardingActivity_2 : AppCompatActivity() {


    //val layout : ConstraintLayout = findViewById(R.id.constraint_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding2)

        val layout = findViewById<ConstraintLayout>(R.id.constraint_layout)

        layout.setOnClickListener {
            val intent = Intent(this, BoardingActivity_3::class.java)
            finish()
            startActivity(intent)
        }
    }
}