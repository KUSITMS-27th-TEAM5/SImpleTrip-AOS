package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.PointRequestBody
import com.kusitms.ovengers.data.ResponseGetPoint
import com.kusitms.ovengers.data.ResponseNewToken
import com.kusitms.ovengers.data.ResponseSetPoint
import com.kusitms.ovengers.databinding.FragmentStoreDetail2Binding
import com.kusitms.ovengers.databinding.FragmentStoreDetail3Binding
import com.kusitms.ovengers.databinding.FragmentStoreDetailBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreDetailFragment_3 : Fragment() {

    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentStoreDetail3Binding
    var num : Int = 0

    fun newInstance() : StoreDetailFragment{
        return StoreDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreDetail3Binding.inflate(inflater, container, false)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")


        getPoint(accessToken)

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val storeFragment = StoreFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeFragment)
                addToBackStack(null)
                commit()
            }
        }

        // 포인트 차감 - 다음 페이지로
        binding.btnNext.setOnClickListener {
            // 차감할 포인트
            var inputPoint : String = binding.editText.text.toString()

            // 포인트 차감 API 호출
            setPoint(accessToken, inputPoint)

            val storeFinishFragment = StoreFinishFragment()
            val bundle = Bundle()
            bundle.putString("inputPoint", inputPoint.toString())

            //storeFinishFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeFinishFragment)
                addToBackStack(null)
                commit()
            }
        }

        return binding.root
    }

    // 잔여 포인트 조회 API
    private fun getPoint(accessToken: String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getPoint(bearerToken).enqueue(object : Callback<ResponseGetPoint> {
            override fun onResponse(call: Call<ResponseGetPoint>, response: Response<ResponseGetPoint>) {
                // 오류 코드
                var code: String? = response.body()?.code.toString()
                if (response.isSuccessful) {
                    val point : Int? = response.body()?.data
                    Log.d("GetPoint Response Message : ", response.message())
                    Log.d("Point Response : ", point.toString())

                    // 잔여 포인트 화면에 띄우기
                    binding.point.text = point.toString()

                } else {
                    if (code == "-5001") { // 토큰 만료
                        newToken(bearerToken) // 토큰 재발급 함수 호출
                    }
                    Log.d("Oauth Login Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseGetPoint>, t: Throwable) {
                Log.d("Oauth Login Response : ", "Fail 2")
            }
        })
    }

    // 토큰 재발급 API
    private fun newToken(bearerToken: String) {
        retAPIS.newToken(bearerToken).enqueue(object : Callback<ResponseNewToken> {
            override fun onResponse(call: Call<ResponseNewToken>, response: Response<ResponseNewToken>) {
                if (response.isSuccessful) {
                    val accessToken : String = response.body()?.attribute?.accessToken.toString()
                    Log.d("New AccessToken Response : ", response.message())
                    Log.d("New AccessToken Response : ", accessToken)

                    // 포인트 조회 함수 재호출
                    getPoint(accessToken)

                } else {
                    Log.d("New Token Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseNewToken>, t: Throwable) {
                Log.d("New Token Response : ", "Fail 2")
            }
        })
    }

    // 포인트 차감 API
    private fun setPoint(accessToken: String, inputPoint : String) {
        val bearerToken = "Bearer $accessToken" // Bearer 추가
        var point : Int = inputPoint.toInt() // Int 형변환
        retAPIS.setPoint(bearerToken, PointRequestBody(point)).enqueue(object : Callback<ResponseSetPoint> {
            override fun onResponse(call: Call<ResponseSetPoint>, response: Response<ResponseSetPoint>) {
                // 오류 코드
                var code: String? = response.body()?.code.toString()
                if (response.isSuccessful) {
                    val point = response.body()?.data
                    Log.d("Set Point Response Message : ", response.message())
                    Log.d("Set Point Result : ", point.toString())

                } else {
                    if (code == "-5001") { // 토큰 만료
                        newToken(bearerToken) // 토큰 재발급 함수 호출
                    }
                    Log.d("Set Point Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseSetPoint>, t: Throwable) {
                Log.d("Set Point Response : ", "Fail 2")
            }
        })
    }

}