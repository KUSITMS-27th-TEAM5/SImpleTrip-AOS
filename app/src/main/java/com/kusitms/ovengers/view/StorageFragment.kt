package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.R


class StorageFragment : Fragment() {
    fun newInstance() : StorageFragment{
        return StorageFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_storage, container, false)
    }
} // 커밋용