package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.StorageDetailAdapter
import com.kusitms.ovengers.StorageDetailViewModel
import com.kusitms.ovengers.StorageViewModel
import com.kusitms.ovengers.data.ResponseCarrierInfo
import com.kusitms.ovengers.data.ResponseTicketExist
import com.kusitms.ovengers.databinding.FragmentStorageDetailBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorageDetailFragment : Fragment() {

    lateinit var binding: FragmentStorageDetailBinding
    private lateinit var retAPIS: APIS
    private lateinit var viewModel : StorageDetailViewModel
    private lateinit var storageDetailAdapter : StorageDetailAdapter

    fun newInstance() : StorageDetailFragment{
        return StorageDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_storage_detail, container, false)

        // Data Binding
        binding = FragmentStorageDetailBinding.inflate(inflater, container, false)

        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // 뒤로 가기 버튼
        binding.btnBack.setOnClickListener{
            val storageFragment = StorageFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storageFragment)
                addToBackStack(null)
                commit()
            }
        }

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")
        val carrierId = MyApplication.prefs.getString("carrierId", "1")

        Log.d("StorageDetailFragment", "carrierId : $carrierId")

        // 유저 이름 설정
        binding.username.text = username

        // 캐리어 정보 조회 API 호출
        getCarrierInfo(accessToken, carrierId)

        // View Model
        viewModel = ViewModelProvider(this).get(StorageDetailViewModel::class.java)

        // StorageDetailAdapter 클릭 이벤트 구현
        storageDetailAdapter = StorageDetailAdapter { task ->

            // Clicked Ticket Id
            Log.d("StorageDetailFragment", "Clicked Ticket Id: ${task.id}")
            var ticketId = task.id.toString()
            MyApplication.prefs.setString("ticketId", ticketId)
            var carrierId = MyApplication.prefs.getString("carrierId", "1")

            getTicketExist(accessToken, carrierId, ticketId)
        }

        // RecyclerView
        val recyclerView : RecyclerView = binding.recyclerview
        recyclerView.adapter = storageDetailAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        // RecyclerView - ViewModel
        viewModel.ticketList.observe(viewLifecycleOwner) { ticketList ->
            storageDetailAdapter.updateList(ticketList)
        }

        return binding.root
    }

    //  캐리어 선택 > 정보 조회 API
    private fun getCarrierInfo(accessToken : String, carrierId : String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getCarrierInfo(bearerToken, carrierId).enqueue(object : Callback<ResponseCarrierInfo> {
            override fun onResponse(call: Call<ResponseCarrierInfo>, response: Response<ResponseCarrierInfo>) {
                if (response.isSuccessful) {
                    var name = response.body()?.data?.name
                    var start = response.body()?.data?.startDate
                    var end = response.body()?.data?.endDate
                    Log.d( "getStorage Response name:",  name.toString())
                    Log.d( "getStorage Response name:",  start.toString())
                    Log.d( "getStorage Response name:",  end.toString())

                    binding.tripName.text = name.toString()
                    binding.start.text = start.toString()
                    binding.end.text = end.toString()

                } else {
                    Log.d("getCarrierInfo :", "Error 1")
                }
            } override fun onFailure(call: Call<ResponseCarrierInfo>, t: Throwable) {
                Log.d("getCarrierInfo :", "Error 2")
            }
        })
    }

    // 티켓 기록 존재 여부 조회 API
    private fun getTicketExist(accessToken : String, carrierId : String, ticketId : String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getTicketExist(bearerToken, carrierId, ticketId).enqueue(object : Callback<ResponseTicketExist> {
            override fun onResponse(call: Call<ResponseTicketExist>, response: Response<ResponseTicketExist>) {
                if (response.isSuccessful) { // 이미 리뷰를 작성했을 경우
                    Log.d( "ResponseTicketExist :",  "리뷰")
                    // 리뷰 수정 페이지로 이동
                    goReviewEdit()
                } else {
                    Log.d("ResponseTicketExist :", "작성된 리뷰 없음")
                    // 리뷰 작성 페이지로 이동
                    goReview()
                }
            } override fun onFailure(call: Call<ResponseTicketExist>, t: Throwable) {
                Log.d("ResponseTicketExist :", "Error 2")
            }
        })
    }

    // 리뷰 작성 전 > 리뷰 작성 페이지로 이동
    private fun goReview() {
        val reviewFragment = ReviewFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.constraint_layout, reviewFragment)
            addToBackStack(null)
            commit()
        }
    }

    // 리뷰 작성 후 > 리뷰 수정 페이지로 이동
    private fun goReviewEdit() {
        val reviewEditFragment = ReviewEditFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.constraint_layout, reviewEditFragment)
            addToBackStack(null)
            commit()
        }
    }
}
