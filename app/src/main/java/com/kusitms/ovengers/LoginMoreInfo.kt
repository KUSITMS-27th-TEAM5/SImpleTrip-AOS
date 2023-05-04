package com.kusitms.ovengers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kusitms.ovengers.databinding.ActivityLoginMoreInfoBinding


class LoginMoreInfo : AppCompatActivity(){

    val REQ_GALLERY = 1


    //남녀 성별 초기값 0
    //클릭시 1로 변환시키고 넘겨줌

    var gender = ""

    val binding by lazy { ActivityLoginMoreInfoBinding.inflate(layoutInflater) }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.ImgUserBtn.setOnClickListener {
            Toast.makeText(this,"꾹 눌러주세요",Toast.LENGTH_SHORT).show()
        }
        // 유저 이미지 선택
        registerForContextMenu(binding.ImgUserBtn)

        //유저 성별 선택

        binding.maleBtn.setOnClickListener {

            binding.maleBtn.setBackgroundColor(Color.parseColor("#855EFF"))
            binding.femaleBtn.setBackgroundColor(Color.parseColor("#E0E0E0"))

            gender = "남자"
        }
        binding.femaleBtn.setOnClickListener {

            binding.maleBtn.setBackgroundColor(Color.parseColor("#E0E0E0"))
            binding.femaleBtn.setBackgroundColor(Color.parseColor("#855EFF"))


            gender = "여자"
        }




        //달력
        val cal = Calendar.getInstance()


        binding.datePickBtn.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                binding.yearText.text = "${y}"
                binding.monthText.text = "${m}"
                binding.dayText.text = "${d}"
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.user_img_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.user_default_img -> {
                defaultImg()
            }

            R.id.openGallery -> {
                openGallery()
            }
        }

        return super.onContextItemSelected(item)
    }

    //갤러리 열기
    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent,REQ_GALLERY)
    }

    //기본 이미지 세팅
    fun defaultImg() {
        binding.ImgUser.setImageResource(R.drawable.ic_profile_default)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK) {
            when(requestCode) {
                REQ_GALLERY -> {
                    data?.data?.let {uri ->
                        binding.ImgUser.setImageURI(uri)
                    }
                }
            }
        }

    }



} // 커밋용



