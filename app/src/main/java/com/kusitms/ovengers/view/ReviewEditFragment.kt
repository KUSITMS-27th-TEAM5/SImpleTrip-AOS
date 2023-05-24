package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.ResponseGetMemo
import com.kusitms.ovengers.data.ResponseTicketExist
import com.kusitms.ovengers.databinding.FragmentNotifyBinding
import com.kusitms.ovengers.databinding.FragmentReviewEditBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewEditFragment : Fragment() {

    lateinit var binding : FragmentReviewEditBinding
    private lateinit var retAPIS: APIS

    fun newInstance() : NotifyFragment{
        return NotifyFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review_edit, container, false)

        // Data Binding
        binding = FragmentReviewEditBinding.inflate(inflater, container, false)

        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")
        val carrierId = MyApplication.prefs.getString("carrierId", "1")
        val ticketId = MyApplication.prefs.getString("ticketId", "1")
        val ticketTitle = MyApplication.prefs.getString("ticketTitle", "title")

        // 티켓 제목 설정
        binding.name.text = ticketTitle.toString()

        // 뒤로 가기 버튼
        binding.btnBack.setOnClickListener{
            val storageDetailFragment = StorageDetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storageDetailFragment)
                addToBackStack(null)
                commit()
            }
        }

        // 티켓 기록 조회 API 호출
        getMemo(accessToken, carrierId, ticketId)

        return binding.root
    }

    // 티켓 기록 존재 여부 조회 API
    private fun getMemo(accessToken : String, carrierId : String, ticketId : String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getMemo(bearerToken, carrierId, ticketId).enqueue(object : Callback<ResponseGetMemo> {
            override fun onResponse(call: Call<ResponseGetMemo>, response: Response<ResponseGetMemo>) {
                if (response.isSuccessful) {
                    Log.d( "ResponseGetMemo :",  "Success")
                    val url = response.body()?.data?.imageUrl.toString()
                    val text = response.body()?.data?.content.toString()

                    // 이미지 설정
                    context?.let { Glide.with(it).load(url).error(R.drawable.bg_store_detail_ex).into(binding.img) };

                    // 텍스트 설정
                    binding.text.setText(text)

                } else {
                    Log.d("ResponseGetMemo :", "Error 1")
                }
            } override fun onFailure(call: Call<ResponseGetMemo>, t: Throwable) {
                Log.d("ResponseGetMemo :", "Error 2")
            }
        })
    }

}