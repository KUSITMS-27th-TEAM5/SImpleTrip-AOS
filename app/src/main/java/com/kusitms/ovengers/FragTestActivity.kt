package com.kusitms.ovengers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragTestActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_test)

        val transaction = manager.beginTransaction()
        val HomeFragment2 = HomeFragment2()
        transaction.replace(R.id.frameArea, HomeFragment2)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}