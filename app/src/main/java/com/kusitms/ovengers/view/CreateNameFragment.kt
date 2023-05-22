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
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.RequestMakeCarrier
import com.kusitms.ovengers.data.ResponseMakeCarrier
import com.kusitms.ovengers.data.ResponseSignUp
import com.kusitms.ovengers.databinding.FragmentChooseDestinationBinding
import com.kusitms.ovengers.databinding.FragmentCreateNameBinding
import com.kusitms.ovengers.retrofit.APIS
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.security.auth.callback.Callback


class CreateNameFragment : Fragment() {
    private lateinit var retAPIS: APIS

    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
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


        val startDay= MyApplication.prefs.getString("startDay","String")
       val endDay =  MyApplication.prefs.getString("endDay","String")

        val pattern = DateTimeFormatter.ofPattern("yyyy-M-d")
         var startdday = LocalDate.parse(startDay, pattern)
        var enddday = LocalDate.parse(endDay,pattern)

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
                MyApplication.prefs.setString("carrierName", carrierName)
                val data = RequestMakeCarrier(
                    MyApplication.prefs.getString("destination","String"),
                            MyApplication.prefs.getString("carrierName","String"),
                    startdday,
                    enddday

                )

                val hActivity = activity as HomeActivity
                hActivity.carrierMakeSuccess()

            }

        }


    }


}