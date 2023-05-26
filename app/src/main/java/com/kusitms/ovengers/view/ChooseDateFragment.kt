package com.kusitms.ovengers.view

import android.app.DatePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
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
import com.kusitms.ovengers.databinding.FragmentChooseDateBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ChooseDateFragment : Fragment() {

    fun newInstance() : ChooseDateFragment{
        return ChooseDateFragment()
    }

    var startYear = ""
    var startMonth = ""
    var startDate = ""
    var startDay = ""


    var endYear = ""
    var endMonth = ""
    var endDate = ""
    var endDay = ""



    private var _binding : FragmentChooseDateBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val hActivity = activity as HomeActivity
//        hActivity.HideBottomNav(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChooseDateBinding.inflate(inflater,container,false)

        val view = binding.root
        return view





        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //날짜 형태
      //  val dateFormat: DateFormat = SimpleDateFormat("yyyy년mm월dd일")
        //date타입
        val date: Date = Date(binding.calendarView.date)


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




        binding.btnNext.setOnClickListener {
            if (startDay == "" || endDay == "") {
                Toast.makeText(context, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show()
            } else {


               MyApplication.prefs.setString("startDay",startDay)
                MyApplication.prefs.setString("endDay",endDay)
                val hActivity = activity as HomeActivity
                hActivity.step1Step2()
            } // 얍


        }
//뒤로가기
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }


    }


} // 커밋용