package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kusitms.ovengers.R

class NotDeveloped : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // beforeBtn을 누르면 이전 화면으로 돌아가게 설정 -> 아마 navigation xml 파일에서 프래그먼트 액션 대상 설정 필요
        val beforeBtn = view.findViewById<ImageView>(R.id.beforeBtn)
        beforeBtn.setOnClickListener {
            Log.d("beforebtn pushed", "이전 화면 눌림")

            val fragmentManager = requireActivity().supportFragmentManager
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStack()
            } else {
                requireActivity().finish()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_not_developed, container, false)
    }


}