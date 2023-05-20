package com.kusitms.ovengers.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kusitms.ovengers.HomeActivity
import com.kusitms.ovengers.R
import com.kusitms.ovengers.databinding.FragmentChooseDestinationBinding
import com.kusitms.ovengers.databinding.FragmentCreateNameBinding


class CreateNameFragment : Fragment() {

    var carrierName = ""

    private var _binding : FragmentCreateNameBinding? = null
    private val binding get() = _binding!!

    fun newInstance() : CreateNameFragment{
        return CreateNameFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNameBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.raw.carrierr).into(binding.imgCarrier)





        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }



        binding.finish.setOnClickListener {
            carrierName = binding.edittextCarrierName.text.toString()
            Log.d("carrier make finish","$carrierName 생성 완료")
            if(carrierName=="") {
                Toast.makeText(context,"캐리어 이름을 입력해주세요",Toast.LENGTH_SHORT).show()
            } else {
                val hActivity = activity as HomeActivity
                hActivity.carrierMakeSuccess()

            }

        }
    }


}