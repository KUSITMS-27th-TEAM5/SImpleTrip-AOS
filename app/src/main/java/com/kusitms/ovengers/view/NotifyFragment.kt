package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.NotifyAdapter
import com.kusitms.ovengers.NotifyViewModel
import com.kusitms.ovengers.R
import com.kusitms.ovengers.StorageAdapter
import com.kusitms.ovengers.StorageViewModel
import com.kusitms.ovengers.databinding.FragmentNotifyBinding
import com.kusitms.ovengers.databinding.FragmentStorageBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance

class NotifyFragment : Fragment() {

    lateinit var binding : FragmentNotifyBinding
    private lateinit var retAPIS: APIS
    private lateinit var viewModel : NotifyViewModel
    private lateinit var notifyAdapter : NotifyAdapter

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
        val view = inflater.inflate(R.layout.fragment_notify, container, false)

        // Data Binding
        binding = FragmentNotifyBinding.inflate(inflater, container, false)

        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")

        // Notify 페이지 이동
        binding.btnBack.setOnClickListener{
            // 프레그먼트 종료하는 로직 구현해랏
        }

        // View 모델
        viewModel = ViewModelProvider(this).get(NotifyViewModel::class.java)

        // NotifyeAdapter 초기화
        notifyAdapter = NotifyAdapter {

        }

        // RecyclerView
        val recyclerView : RecyclerView = binding.recyclerview
        recyclerView.adapter = notifyAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // RecyclerView - ViewModel
        viewModel.alarm.observe(viewLifecycleOwner) { alarm ->
            notifyAdapter.updateAlarm(alarm)
        }

        return binding.root
    }

}