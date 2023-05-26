package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kusitms.ovengers.HomeActivity
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.PointRequestBody
import com.kusitms.ovengers.data.RequestMakeCarrier
import com.kusitms.ovengers.data.ResponseMakeCarrier
import com.kusitms.ovengers.data.ResponseSetPoint
import com.kusitms.ovengers.data.ResponseSignUp
import com.kusitms.ovengers.databinding.FragmentChooseDestinationBinding
import com.kusitms.ovengers.databinding.FragmentCreateNameBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.security.auth.callback.Callback


class CreateNameFragment : Fragment() {
    private lateinit var retAPIS: APIS

    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    var carrierName = ""

    private var _binding : FragmentCreateNameBinding? = null
    private val binding get() = _binding!!

    fun newInstance() : CreateNameFragment{
        return CreateNameFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNameBinding.inflate(inflater,container,false)
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.raw.carrierr).into(binding.imgCarrier)

        val startDay= MyApplication.prefs.getString("startDay","String")
        val endDay =  MyApplication.prefs.getString("endDay","String")


        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }



        binding.finish.setOnClickListener {
            carrierName = binding.edittextCarrierName.text.toString()
            Log.d("carrier make finish","$carrierName 생성 완료")
            if(carrierName=="") {
                Toast.makeText(context,"캐리어 이름을 입력해주세요",Toast.LENGTH_SHORT).show()
            } else {

                MyApplication.prefs.setString("carrierName", carrierName)



                val destination =MyApplication.prefs.getString("destination","String")
                val name= carrierName

                // 함수 호출
//                createCarrier(accessToken, destination, name, startDay, endDay)
                createCarrier(accessToken, destination, name, startDay, endDay)

                val hActivity = activity as HomeActivity
                hActivity.carrierMakeSuccess()


            }

        }


    }

    private fun createCarrier(accessToken: String, destination : String, name : String, startday : String, endday : String) {
        val bearerToken = "Bearer $accessToken" // Bearer 추가
        retAPIS.addCarrier(
            bearerToken,
            RequestMakeCarrier(destination, name, startday, endday)
        )
            .enqueue(object : retrofit2.Callback<ResponseMakeCarrier> {
            override fun onResponse(call: Call<ResponseMakeCarrier>, response: Response<ResponseMakeCarrier>) {
                if (response.isSuccessful) {
                    val resultMessage = response.body()?.resultMessage.toString()
                    Log.d("addCarrier Response Message : ", resultMessage)

                } else {
                    Log.d("fail1", RequestMakeCarrier(destination, name, startday, endday).toString())
                    Log.d("addCarrier Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseMakeCarrier>, t: Throwable) {
                Log.d("addCarrier Response : ", "Fail 2")
            }
        })
    }

} // 커밋용