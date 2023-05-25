package com.kusitms.ovengers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kusitms.ovengers.data.RequestSignUp
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.data.ResponseSignUp
import com.kusitms.ovengers.databinding.ActivityLoginMoreInfoBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginMoreInfo : AppCompatActivity(){

    val REQ_GALLERY = 1

    private lateinit var retAPIS: APIS
    // API 호출 -> 만료됐음 ! -1004
    // 갱신 API 호출 -> new token 갱신
    // 내가 원래 호출하려던 API


    //남녀 성별 초기값 0
    //클릭시 1로 변환시키고 넘겨줌

    var gender = ""
    var birth = ""
    var pictureUrl= ""

    // SharedPreferences 조희
    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    val userName = MyApplication.prefs.getString("userName","String")
    val email = MyApplication.prefs.getString("email","String")
    var nickName = ""

    // intent 조희
//    var name = intent.getStringExtra("userName")
//    var email = intent.getStringExtra("email")

    val binding by lazy { ActivityLoginMoreInfoBinding.inflate(layoutInflater) }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        binding.editTextName.setText(userName)
        binding.editTextEmail.setText(email)


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
//       birth="${binding.yearText.text}-${binding.monthText.text}-${binding.dayText.text}"
            birth="2000-02-18"
        }

        binding.SignInBtn.setOnClickListener {
            val data =
                RequestSignUp(

                   binding.editTextName.text.toString(),
                   binding.editTextNickname.text.toString(),
                   binding.editTextEmail.text.toString(),
                    gender,
                    birth,
                    "uri"

                )

            val bearerToken = "Bearer $accessToken"
            retAPIS.signUp(bearerToken!!,data).enqueue(object : Callback<ResponseSignUp> {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(call: Call<ResponseSignUp>, response: Response<ResponseSignUp>) {
                    if (response.isSuccessful) {


                        val accessToken : String = response.body()?.attribute?.accessToken.toString()
                        val refreshToken : String = response.body()?.attribute?.refreshToken.toString()


                        //새로운 토큰 저장
                        MyApplication.prefs.setString("accessToken",accessToken)
                        MyApplication.prefs.setString("refreshToken",refreshToken)


                        Log.d("AccessToken Response : ", accessToken)
                        Log.d("AccessToken refreshToken : ", refreshToken)
                            Log.d("signUp response ","회원가입 성공")
                        Toast.makeText(baseContext,"회원가입 성공",Toast.LENGTH_SHORT).show()

                        moveToHome()
                    } else {
                        Log.d("signUp Response : ", "Fail 1")
                    }
                } override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                    Log.d("signUp Response : ", "Fail 2")
                }
            })

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


    private fun moveToHome () {
        nickName = binding.editTextNickname.text.toString()
        if (nickName=="") {
            Toast.makeText(baseContext,"닉네임을 입력해주세요",Toast.LENGTH_SHORT).show()
        } else {
            MyApplication.prefs.setString("nickName",nickName)
            val intent = Intent(this, SignUpSuccessActivity::class.java)
            finish()
            startActivity(intent)
        }

    }




} //커밋용



