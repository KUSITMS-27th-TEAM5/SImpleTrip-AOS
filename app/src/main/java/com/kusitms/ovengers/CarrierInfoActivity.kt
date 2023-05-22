package com.kusitms.ovengers

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kusitms.ovengers.data.carrierMoreInfo
import com.kusitms.ovengers.databinding.ActivityCarrierInfoBinding

class CarrierInfoActivity : AppCompatActivity() {
    private lateinit var carrierMoreInfoAdapter: CarrierMoreInfoAdapter
    private lateinit var binding : ActivityCarrierInfoBinding

    private val dataSet = ArrayList<carrierMoreInfo>()
    private var isFabOpen = false
    val REQ_GALLERY = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrierInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//
//        dataSet.add(carrierMoreInfo("aa",R.drawable.dafaultticket))
//        dataSet.add(carrierMoreInfo("aa",R.drawable.osakaticket))
//        dataSet.add(carrierMoreInfo("aa",R.drawable.harukaticket))
//        dataSet.add(carrierMoreInfo("aa",R.drawable.harukacastle))

        carrierMoreInfoAdapter = CarrierMoreInfoAdapter(dataSet)
        binding.ticketRv.adapter = carrierMoreInfoAdapter
        binding.ticketRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //floating action button 열기,닫기
        binding.fabMain.setOnClickListener {
            fab()
        }

        binding.fabLink.setOnClickListener {
            Toast.makeText(this,"fablink",Toast.LENGTH_SHORT).show()
            setLink()
        }
        binding.fabFile.setOnClickListener {
            Toast.makeText(this,"fabfile",Toast.LENGTH_SHORT).show()
        }
        binding.fabPhoto.setOnClickListener {
            Toast.makeText(this,"fabphoto",Toast.LENGTH_SHORT).show()
        openGallery()

        }





    }

    fun setLink() {


        val mDialog = LayoutInflater.from(this).inflate(R.layout.link_dialog,null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialog)
            .setTitle("링크를 입력해주세요")

        val mAlertDialog = mBuilder.show()
//        mBuilder.show()

        val saveBtn = mDialog.findViewById<Button>(R.id.btn_save)
        saveBtn.setOnClickListener {
            val link = findViewById<EditText>(R.id.edittext_link)

            val a = link.text.toString()

            dataSet.add(carrierMoreInfo(a,R.drawable.dafaultticket))
        }

        val noButton = mDialog.findViewById<Button>(R.id.btn_cancle)
        noButton.setOnClickListener {
           mBuilder.show().dismiss()
        }

    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,REQ_GALLERY)
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(resultCode == RESULT_OK) {
//            when(requestCode) {
//                REQ_GALLERY -> {
//                    data?.data?.let {uri ->
//
//
//                        var img_ticket = MediaStore.Images.Media.getBitmap(contentResolver,uri)
//                        dataSet.add(carrierMoreInfo("jj",img_ticket))
////                       img_ticket.setImageURI(uri)
////                        binding.ImgUser.setImageURI(uri)
//
//                    }
//                }
//            }
//        }
//
//    }



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