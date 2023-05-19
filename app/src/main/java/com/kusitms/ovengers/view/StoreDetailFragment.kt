package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.ResponseGetPoint
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.databinding.FragmentStoreDetailBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreDetailFragment : Fragment() {

    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentStoreDetailBinding

    fun newInstance() : StoreDetailFragment{
        return StoreDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreDetailBinding.inflate(inflater, container, false)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjgyMjgzOGMxYzhiZjllZGNmMWY1MDUwNjYyZTU0YmNiMWFkYjViNWYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI2OTkwNzc3OTE2MS1ubWhtNnI1Mzk0aGw0bTN2dDFmM2MyMTBudjBkaWNxNS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjY5OTA3Nzc5MTYxLTM1dGl1MmVrcGQ4c3FtMDJwYTd1ZWg0ZzRwYjNlZ2dyLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTEzMTIxMTI1OTA5MDUzNTYzODQxIiwiZW1haWwiOiJraW0wMDAyMTgzNEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6Inl1amVvbmcgS2ltIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FHTm15eFpEOWozZ2h1ZW5zQnF6MThrYTFkMlF0ZV95VUlrT1o0OG9OMlBFX0E9czk2LWMiLCJnaXZlbl9uYW1lIjoieXVqZW9uZyIsImZhbWlseV9uYW1lIjoiS2ltIiwibG9jYWxlIjoia28iLCJpYXQiOjE2ODQ1MTU3NDksImV4cCI6MTY4NDUxOTM0OX0.gHItpYSBGc0c2_V1zu9G5eog1BLto_KLfyh-surjDb07-p5Xv5yThl7j7sY6svCit-QjXW32vdKEP82DEjvOjXwEzyFGokjrxHAwitXj2MuhWPTSX69O4dIMpCvHwarnlXTA1MkNJYld2XL-wK7SlNr-PlMVo13kzGAWJTITboM27Yr0e5f8SJXHtObc_sjVudcHi_3jDF1YHM_prNeh_NMZdbunDZGogbDVrea8vGs8fZKk8agSkYZtH7-vD49Ji9RUoSDYF5oM8znXiB0IWQcqj1IJKlWcEbTReAwmA390mekUs3WuUzvzlVXPdYIiTBr4dBjotjU3VETf6iNwfQ"

        // 상품별 값
        val location: List<String> = listOf("제주도", "미국", "부산", "서울", "일본", "독일")
        val name : List<String> = listOf("그랜드 조선 제주", "로젠 인 레이크 부에나 비스타 올랜도", "해운대 블루라인 파크", "앰배서더 서울 풀만 호텔", "도쿄 - 오사카", "잘츠부르크와 호수 지구")
        val stars: List<String> = listOf("4.8", "4.3", "5.0", "4.0", "4.0", "4.9")
        val price: List<String> = listOf("330,000 원", "115,384 원", "32,000 원", "250,000 원", "30,000 원", "250,000 원")

        // Store Bundle 값 받아오기
        val bundle = arguments
        if (bundle != null) {
            val num = bundle.getInt("Num")
            Log.d("StoreDetailBundle : ", "$num")

            // 상품별 text 세팅
            binding.name.text = name[num-1].toString()
            binding.stars.text = stars[num-1]
            binding.price.text = price[num-1]

            // 상품별 이미지 세팅
            if (num==1) {
                binding.image.setImageResource(R.drawable.ic_store_img_1)
            } else if (num==2) {
                binding.image.setImageResource(R.drawable.ic_store_img_2)
            } else if (num==3) {
                binding.image.setImageResource(R.drawable.ic_store_img_3)
            } else if (num==4) {
                binding.image.setImageResource(R.drawable.ic_store_img_4)
            } else if (num==5) {
                binding.image.setImageResource(R.drawable.ic_store_img_5)
            } else if (num==6) {
                binding.image.setImageResource(R.drawable.ic_store_img_6)
            }

        }

        getPoint(accessToken)


        return binding.root
    }

    // 잔여 포인트 조회
    private fun getPoint(accessToken: String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getPoint(bearerToken).enqueue(object : Callback<ResponseGetPoint> {
            override fun onResponse(call: Call<ResponseGetPoint>, response: Response<ResponseGetPoint>) {
                // 오류 코드
                var code: String? = response.body()?.code.toString()
                if (response.isSuccessful) {
                    val accessToken : String = response.body()?.page.toString()
                    val point : Int = response.body()?.page!!.number
                    Log.d("GetPoint Response Message : ", response.message())
                    Log.d("AccessToken Response : ", accessToken)

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

    // 토큰 재발급
    private fun newToken(bearerToken: String) {
        retAPIS.getPoint(bearerToken).enqueue(object : Callback<ResponseGetPoint> {
            override fun onResponse(call: Call<ResponseGetPoint>, response: Response<ResponseGetPoint>) {
                // 오류 코드
                var code: String? = response.body()?.code.toString()
                if (response.isSuccessful) {
                    val accessToken : String = response.body()?.page.toString()
                    Log.d("oauthGoogle Response Message : ", response.message())
                    Log.d("AccessToken Response : ", accessToken)

                } else {
                    Log.d("Oauth Login Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseGetPoint>, t: Throwable) {
                Log.d("Oauth Login Response : ", "Fail 2")
            }
        })
    }
}