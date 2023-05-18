package com.kusitms.ovengers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusitms.ovengers.R
import com.kusitms.ovengers.databinding.FragmentStorageRetrospectBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance

class StorageRetrospectFragment : Fragment() {

    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentStorageRetrospectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // View Binding
        val view = inflater.inflate(R.layout.fragment_storage_retrospect, container, false)
        binding = FragmentStorageRetrospectBinding.inflate(inflater, container, false)

        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)



        return binding.root
    }

}