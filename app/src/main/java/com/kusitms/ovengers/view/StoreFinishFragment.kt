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

        // 확인 버튼
        binding.btnNext.setOnClickListener {
            val storeFragment = StoreFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeFragment)
                addToBackStack(null)
                commit()
            }
        }

        return binding.root
    }
} // 커밋용