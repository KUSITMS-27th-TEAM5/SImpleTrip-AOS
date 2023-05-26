package com.kusitms.ovengers

import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.data.*
import com.kusitms.ovengers.databinding.ActivityCarrierInfoBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import com.kusitms.ovengers.view.HomeFragment
import com.kusitms.ovengers.view.NotDeveloped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarrierInfoActivity : AppCompatActivity() {
    private lateinit var carrierMoreInfoAdapter: CarrierMoreInfoAdapter
    lateinit var binding : ActivityCarrierInfoBinding
    private lateinit var retAPIS: APIS
    private lateinit var viewModel : CarrierMoreInfoViewModel




//    private val dataSet = ArrayList<carrierMoreInfo>()
    private var isFabOpen = false
    val REQ_GALLERY = 2

    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrierInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"

        //var carrierId = MyApplication.prefs.getString("carrierId", "1")

        binding.btnBack.setOnClickListener{

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)


        }



        viewModel = ViewModelProvider(this).get(CarrierMoreInfoViewModel::class.java)

        carrierMoreInfoAdapter = CarrierMoreInfoAdapter()

        val recyclerView : RecyclerView = binding.ticketRv

        recyclerView.adapter = carrierMoreInfoAdapter
        recyclerView.layoutManager=GridLayoutManager(baseContext, 1)

        viewModel.ticketList.observe(this, Observer { ticketList->
            carrierMoreInfoAdapter.updateList(ticketList)


            // 클릭시 티켓 확대
            carrierMoreInfoAdapter.itemClick = object :CarrierMoreInfoAdapter.ItemClick{
                override  fun onClick(view: View, position: Int) {

                    val intent = Intent(baseContext, TicketDetailActivity::class.java )
                    intent.putExtra("ticketName",ticketList[position].title)
                    intent.putExtra("ticketUrl",ticketList[position].ticketUrl)
                    startActivity(intent)


                }
            }


            binding.fabMain.setOnClickListener {
                fab()
            }

            binding.fabLink.setOnClickListener {
                Toast.makeText(this,"fablink",Toast.LENGTH_SHORT).show()
                setLink()
            }
            binding.fabFile.setOnClickListener {

                val fragment = NotDeveloped()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.carrierInfo, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            binding.fabPhoto.setOnClickListener {
//                Toast.makeText(this,"fabphoto",Toast.LENGTH_SHORT).show()
//                openGallery()
                val fragment = NotDeveloped()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.carrierInfo, fragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }

        })





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

        val Id = MyApplication.prefs.getString("id","")

       // var mTextView = findViewById<TextView>(R.id.ticketName)

        val et = EditText(this)
        et.gravity = Gravity.CENTER
        val builder = AlertDialog.Builder(this)
            .setTitle("링크 입력")
            .setView(et)
            .setPositiveButton("확인",
            DialogInterface.OnClickListener { dialog, which ->

               // mTextView.setText(et.text)

                //API 연결시 수정
                setLinkAPI(accessToken,Id.toInt(),"LINK",et.text.toString())
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


    //티켓 이름 변경
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


    //링크 연결 API
    private fun setLinkAPI(accessToken: String, id : Int, type :String, url : String ) {
        val bearerToken = "Bearer $accessToken" // Bearer 추가
        retAPIS.postLinkUrl(
            bearerToken,
            RequestPostLinkUrl(id, type, url)
        )
            .enqueue(object : retrofit2.Callback<ResponsePostLinkUrl> {
                override fun onResponse(call: Call<ResponsePostLinkUrl>, response: Response<ResponsePostLinkUrl>) {
                    if (response.isSuccessful) {
                        val resultMessage = response.body().toString()
                        Log.d("addCarrier Response Message : ", resultMessage)

                    } else {

                        Log.d("addCarrier Response : ", "Fail 1")
                    }
                } override fun onFailure(call: Call<ResponsePostLinkUrl>, t: Throwable) {
                    Log.d("addCarrier Response : ", "Fail 2")
                }
            })
    }






} // 커밋용