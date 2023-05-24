package com.kusitms.ovengers

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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
        dataSet.add(carrierMoreInfo("aa",R.drawable.dafaultticket))
        dataSet.add(carrierMoreInfo("aa",R.drawable.osakaticket))
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


       // 클릭시 티켓 확대
        carrierMoreInfoAdapter.itemClick = object :CarrierMoreInfoAdapter.ItemClick{
            override  fun onClick(view: View, position: Int) {



            }
        }

        //길게 클릭시 이름 편집
        carrierMoreInfoAdapter.itemLongClick = object:CarrierMoreInfoAdapter.ItemLongClick,
            CarrierAdapter.ItemLongClick {
            override fun onLongClick(view: View, position: Int) {
                 setTitle()
            }
        }




    }

    //링크 입력
    fun setLink() {



        val et = EditText(this)
        et.gravity = Gravity.CENTER
        val builder = AlertDialog.Builder(this)
            .setTitle("링크 입력")
            .setView(et)
            .setPositiveButton("확인",
            DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, et.text,Toast.LENGTH_SHORT).show()
            })
        builder.show()

    }

    //이미지 입력
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,REQ_GALLERY)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK) {
            when(requestCode) {
                REQ_GALLERY -> {
                    data?.data?.let {uri ->
//                        binding.ImgUser.setImageURI(uri)
                         val a = findViewById<ImageView>(R.id.img_ticket)
                        a.setImageURI(uri)

                    }
                }
            }
        }

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
////                        var img_ticket = MediaStore.Images.Media.getBitmap(contentResolver,uri)
////                        dataSet.add(carrierMoreInfo("jj",img_ticket))
//                       img_ticket.setImageURI(uri)
//                        binding.ImgUser.setImageURI(uri)
//
//                    }
//                }
//            }
//        }
//
//    }

    fun setTitle() {
        val et = EditText(this)
        et.gravity = Gravity.CENTER
        val builder = AlertDialog.Builder(this)
            .setTitle("티켓 이름 입력")
            .setView(et)
            .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, which ->
                    findViewById<TextView>(R.id.ticketName).setText(et.text)
                })
        builder.show()
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




} // 커밋용