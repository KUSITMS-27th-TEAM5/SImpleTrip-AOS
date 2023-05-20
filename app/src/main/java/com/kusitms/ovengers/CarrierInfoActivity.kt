package com.kusitms.ovengers

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kusitms.ovengers.data.carrierMoreInfo
import com.kusitms.ovengers.databinding.ActivityCarrierInfoBinding

class CarrierInfoActivity : AppCompatActivity() {
    private lateinit var carrierMoreInfoAdapter: CarrierMoreInfoAdapter
    private lateinit var binding : ActivityCarrierInfoBinding

    private val dataSet = ArrayList<carrierMoreInfo>()
    private var isFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrierInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dataSet.add(carrierMoreInfo(R.drawable.dafaultticket))
        dataSet.add(carrierMoreInfo((R.drawable.osakaticket)))
        dataSet.add(carrierMoreInfo(R.drawable.harukaticket))
        dataSet.add(carrierMoreInfo(R.drawable.harukacastle))

        carrierMoreInfoAdapter = CarrierMoreInfoAdapter(dataSet)
        binding.ticketRv.adapter = carrierMoreInfoAdapter
        binding.ticketRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //floating action button 열기,닫기
        binding.fabMain.setOnClickListener {
            fab()
        }

        binding.fabLink.setOnClickListener {
            Toast.makeText(this,"fablink",Toast.LENGTH_SHORT).show()
        }
        binding.fabFile.setOnClickListener {
            Toast.makeText(this,"fabfile",Toast.LENGTH_SHORT).show()
        }
        binding.fabPhoto.setOnClickListener {
            Toast.makeText(this,"fabphoto",Toast.LENGTH_SHORT).show()
        }


    }



    //floating action button
    private fun fab() {

        if(isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabLink,"translationY",0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabFile,"translationY",0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPhoto,"translationY",0f).apply { start() }
        } else {
            ObjectAnimator.ofFloat(binding.fabLink,"translationY",-200f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabFile,"translationY",-400f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabPhoto,"translationY",-600f).apply { start() }
        }
        isFabOpen =!isFabOpen
    }




}