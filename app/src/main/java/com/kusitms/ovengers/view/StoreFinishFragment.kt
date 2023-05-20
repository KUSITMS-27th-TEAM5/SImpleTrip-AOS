package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.R
import com.kusitms.ovengers.databinding.FragmentStoreDetailBinding
import com.kusitms.ovengers.databinding.FragmentStoreFinishBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance

class StoreFinishFragment : Fragment() {

    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentStoreFinishBinding
    var num : Int = 0

    fun newInstance() : StoreFinishFragment{
        return StoreFinishFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStoreFinishBinding.inflate(inflater, container, false)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"

        // 뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val storeDetailFragment = StoreDetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetailFragment)
                addToBackStack(null)
                commit()
            }
        }

        // 확인 버튼
        binding.btnNext.setOnClickListener {
            val homeFragment = HomeFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, homeFragment)
                addToBackStack(null)
                commit()
            }
        }

        return binding.root
    }
}