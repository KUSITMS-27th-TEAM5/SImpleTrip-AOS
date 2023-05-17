package com.kusitms.ovengers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragTestActivity : AppCompatActivity() {

    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_test)

        val transaction = manager.beginTransaction()
        val HomeFragment = HomeFragment()
        transaction.replace(R.id.frameArea, HomeFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}