package com.kusitms.ovengers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


class LoginMoreInfo : AppCompatActivity() {



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_more_info)


        //달력
        val cal = Calendar.getInstance()

        val yearText : TextView = findViewById(R.id.yearText)
        val monthText : TextView = findViewById(R.id.monthText)
        val dateText : TextView = findViewById(R.id.dayText)


        val datePickBtn : ImageButton = findViewById(R.id.datePickBtn)

        //유저 이미지
        val imgUserBtn : ImageButton = findViewById(R.id.Img_user_btn)
        val imgUser : ImageView = findViewById(R.id.Img_user)





        datePickBtn.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                yearText.text = "${y}"
                monthText.text = "${m}"
                dateText.text = "${d}"
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()

        }


        }


}



