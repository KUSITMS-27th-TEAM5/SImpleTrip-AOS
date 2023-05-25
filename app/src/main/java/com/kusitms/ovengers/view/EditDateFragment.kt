package com.kusitms.ovengers.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kusitms.ovengers.HomeActivity
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.RequestEditCarrierName
import com.kusitms.ovengers.data.RequestEditCarrierPeriod
import com.kusitms.ovengers.data.ResponseEditCarrierName
import com.kusitms.ovengers.data.ResponseEditCarrierPeriod
import com.kusitms.ovengers.databinding.FragmentEditDateBinding
import com.kusitms.ovengers.databinding.FragmentHomeBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class EditDateFragment : Fragment() {

    private lateinit var retAPIS: APIS
    lateinit var binding : FragmentEditDateBinding
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"


    fun newInstance() : EditDateFragment{
        return EditDateFragment()
    }

    var startYear = ""
    var startMonth = ""
    var startDate = ""
    var startDay = ""


    var endYear = ""
    var endMonth = ""
    var endDate = ""
    var endDay = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hActivity = activity as HomeActivity
        hActivity.HideBottomNav(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)



        binding = FragmentEditDateBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date: Date = Date(binding.calendarView.date)

        val id = requireArguments().getInt("id")
        Log.d("id_date",id.toString())


        binding.startDate.setOnClickListener {
//            binding.calendarView.visibility=View.VISIBLE

            binding.calendarView.setOnDateChangeListener { calendarView, y, m, d ->
                startYear = "${y}"
                startMonth = "${m}"
                startDate = "${d}"
                binding.startDate.setText("${y}년 ${m}월 ${d}일")
                binding.startDate.setTextColor(Color.parseColor("#855EFF"))
                if (m<10) {
                    startMonth = "0${m}"
                }
                if (d<10) {
                    startDate = "0${d}"
                }
                startDay = "${startYear}${startMonth}${startDate}"
                Log.d("endday",startDay)
            }

        }

        binding.endDate.setOnClickListener {
//            binding.calendarView.visibility=View.VISIBLE

            binding.calendarView.setOnDateChangeListener { calendarView, y, m, d ->
                endYear = "${y}"
                endMonth = "${m}"
                endDate = "${d}"
                binding.endDate.setText("${y}년 ${m}월 ${d}일")
                binding.endDate.setTextColor(Color.parseColor("#855EFF"))
                if (m<10) {
                    endMonth = "0${m}"
                }
                if (d<10) {
                    endDate = "0${d}"
                }
                endDay = "${endYear}${endMonth}${endDate}"
                Log.d("endday",endDay)
            }
        }


        binding.editFinish.setOnClickListener {
            if (startDay == "" || endDay == "") {
                Toast.makeText(context, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
            } else {


                MyApplication.prefs.setString("startDay",startDay)
                MyApplication.prefs.setString("endDay",endDay)
                editCarrierDate(id,startDay,endDay)

                //뒤로가기
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                requireActivity().supportFragmentManager.popBackStack()
                val hActivity = activity as HomeActivity
                hActivity.HideBottomNav(false)
            }
        }


    }


    //캐리어 날짜 수정 API
    private fun editCarrierDate(id : Int, startDate : String, endDate : String){
        val bearerToken = "Bearer $accessToken"
        retAPIS.editCarrierPeriod(bearerToken, RequestEditCarrierPeriod(id, startDate,endDate)).enqueue(object :
            Callback<ResponseEditCarrierPeriod> {
            override fun onResponse(call: Call<ResponseEditCarrierPeriod>, response: Response<ResponseEditCarrierPeriod>) {
                if (response.isSuccessful) {

                    Log.d("editCarrierPeriod Response : ", response.body().toString())




                } else {
                    Log.d("editCarrierPeriod Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseEditCarrierPeriod>, t: Throwable) {
                Log.d("editCarrierPeriod Response : ", "Fail 2")
            }
        })
    }


}