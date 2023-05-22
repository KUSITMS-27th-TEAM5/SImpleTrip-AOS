package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.Mypage
import com.kusitms.ovengers.R
import com.kusitms.ovengers.databinding.FragmentStoreBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance

class StoreFragment : Fragment() {
    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentStoreBinding
    fun newInstance() : StoreFragment{
        return StoreFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoreBinding.inflate(inflater, container, false)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val username = MyApplication.prefs.getString("username", "username")

        // 유저 네임 설정
        binding.username.text = username

        // Mypage 이동
        binding.btnMypage.setOnClickListener {
            val myPage = Mypage()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, myPage)
                addToBackStack(null)
                commit()
            }

        }

        // 1번 상품
        binding.btn1.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 1)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }

        // 2번 상품
        binding.btn2.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 2)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }


        // 3번 상품
        binding.btn3.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 3)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }

        // 4번 상품
        binding.btn4.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 4)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }

        // 5번 상품
        binding.btn5.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 5)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }

        // 6번 상품
        binding.btn6.setOnClickListener{
            val storeDetail = StoreDetailFragment()
            val bundle = Bundle()
            bundle.putInt("Num", 6)
            storeDetail.arguments = bundle
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, storeDetail)
                addToBackStack(null)
                commit()
            }
        }

        return binding.root
    }
}