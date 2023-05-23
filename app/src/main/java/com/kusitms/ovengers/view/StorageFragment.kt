package com.kusitms.ovengers.view

import android.content.Intent
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
import com.kusitms.ovengers.Mypage
import com.kusitms.ovengers.NotifyActivity
import com.kusitms.ovengers.NotifyFragment
import com.kusitms.ovengers.R
import com.kusitms.ovengers.StorageAdapter
import com.kusitms.ovengers.StorageViewModel
import com.kusitms.ovengers.databinding.FragmentStorageBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance


class StorageFragment : Fragment() {

    lateinit var binding: FragmentStorageBinding
    private lateinit var retAPIS: APIS
    private lateinit var viewModel : StorageViewModel
    private lateinit var storageAdapter : StorageAdapter

    fun newInstance() : StorageFragment{
        return StorageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_storage, container, false)

        // Data Binding
        binding = FragmentStorageBinding.inflate(inflater, container, false)

        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")

        // Notify 페이지 이동
        binding.btnBell.setOnClickListener{
            val notify = NotifyFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, notify)
                addToBackStack(null)
                commit()
            }

        }

        // MyPage 이동
        binding.btnMypage.setOnClickListener {
            val myPage = Mypage()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, myPage)
                addToBackStack(null)
                commit()
            }
        }

        // 유저 이름 설정
        binding.username.text = username

        // View 모델
        viewModel = ViewModelProvider(this).get(StorageViewModel::class.java)

        // StorageAdapter 초기화
        storageAdapter = StorageAdapter {

        }

        // RecyclerView
        val recyclerView : RecyclerView = binding.recyclerGridView
        recyclerView.adapter = storageAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // RecyclerView - ViewModel
        viewModel.storageList.observe(viewLifecycleOwner) { storageList ->
            storageAdapter.updateList(storageList)
        }

        return binding.root
    }
}