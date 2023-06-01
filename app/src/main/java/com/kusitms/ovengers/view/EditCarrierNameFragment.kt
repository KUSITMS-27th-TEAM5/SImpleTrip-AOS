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
import com.kusitms.ovengers.data.RequestDeleteCarrier
import com.kusitms.ovengers.data.RequestEditCarrierName
import com.kusitms.ovengers.data.ResponseDeleteCarrier
import com.kusitms.ovengers.data.ResponseEditCarrierName
import com.kusitms.ovengers.databinding.FragmentEditCarrierNameBinding
import com.kusitms.ovengers.databinding.FragmentHomeBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditCarrierNameFragment : Fragment() {
    // 커밋용

    private lateinit var retAPIS: APIS
    private lateinit var binding : FragmentEditCarrierNameBinding
    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    var carrierName = ""

    fun newInstance() : EditCarrierNameFragment{
        return EditCarrierNameFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)




        binding = FragmentEditCarrierNameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val id = requireArguments().getInt("id")
        Log.d("id_name",id.toString())

        Glide.with(this).load(R.raw.carrierr).into(binding.imgCarrier)
        //수정 완료 버튼
        binding.editFinish.setOnClickListener {

            carrierName = binding.editCarrierName.text.toString()
            Log.d("carrier make finish", "$carrierName 생성 완료")
            if (carrierName == "") {
                Toast.makeText(context, "캐리어 이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {

                MyApplication.prefs.setString("carrierName", carrierName)
                 editCarrierName(id,carrierName)

                //뒤로가기
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                requireActivity().supportFragmentManager.popBackStack()
                val hActivity = activity as HomeActivity
                hActivity.HideBottomNav(false)
            }
        }
    }


    //캐리어 이름 수정 API
    private fun editCarrierName(id : Int, name : String){
        val bearerToken = "Bearer $accessToken"
        retAPIS.editCarrierName(bearerToken, RequestEditCarrierName(id, name)).enqueue(object :
            Callback<ResponseEditCarrierName> {
            override fun onResponse(call: Call<ResponseEditCarrierName>, response: Response<ResponseEditCarrierName>) {
                if (response.isSuccessful) {

                    Log.d("editCarrierName Response : ", response.body().toString())




                } else {
                    Log.d("editCarrierName Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseEditCarrierName>, t: Throwable) {
                Log.d("editCarrierName Response : ", "Fail 2")
            }
        })
    }

}