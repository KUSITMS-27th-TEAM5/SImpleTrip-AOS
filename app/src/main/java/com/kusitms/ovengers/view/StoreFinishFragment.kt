package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.R

class StoreFinishFragment : Fragment() {

    fun newInstance() : StoreFinishFragment{
        return StoreFinishFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_store_finish, container, false)
    }
}