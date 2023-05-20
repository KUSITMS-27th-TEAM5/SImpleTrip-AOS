package com.kusitms.ovengers.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.kusitms.ovengers.*
import com.kusitms.ovengers.data.RequestMakeCarrier
import com.kusitms.ovengers.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var carrierAdapter: CarrierAdapter
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val dataSet = ArrayList<RequestMakeCarrier>()


    fun newInstance() : HomeFragment{
        return HomeFragment()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hActivity = activity as HomeActivity
        hActivity.HideBottomNav(false)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = MyApplication.prefs.getString("userName","String")

        binding.carrierWho.setText("${name} 님의 티켓 캐리어")

        dataSet.add(RequestMakeCarrier("a","d","A","일본 여행"))

        dataSet.add(RequestMakeCarrier("a","d","A","미국 여행"))



        carrierAdapter = CarrierAdapter(dataSet)

        binding.carrierRv.adapter = carrierAdapter
        binding.carrierRv.layoutManager = GridLayoutManager(context,2)

        //캐리어 티켓 페이지이동
        carrierAdapter.itemClick = object :CarrierAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {

                val intent = Intent(activity,CarrierInfoActivity::class.java)
                startActivity(intent)


            }
        }

        //캐리어 편집
        carrierAdapter.itemLongClick = object :CarrierAdapter.ItemLongClick{
            override fun onLongClick(view: View, position: Int) {
               // Toast.makeText(context,"long",Toast.LENGTH_SHORT).show()

                var pop = PopupMenu(context,view)
                pop.menuInflater.inflate(R.menu.carrier_popup,pop.menu)

                pop.setOnMenuItemClickListener { item ->
                    when(item.itemId) {

                        R.id.popup_country->
                            Toast.makeText(context,"country",Toast.LENGTH_SHORT).show()
                        R.id.popup_date->
                            Toast.makeText(context,"date",Toast.LENGTH_SHORT).show()
                        R.id.popup_carrier_name->
                            Toast.makeText(context,"name",Toast.LENGTH_SHORT).show()
                        R.id.popup_delete_carrier->
                            Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show()
                        R.id.popup_cancle->
                            Toast.makeText(context,"cancle",Toast.LENGTH_SHORT).show()

                    }
                    false
                }
                pop.show()
            }
        }

        //캐리어 생성 버튼
        val hActivity = activity as HomeActivity

        binding.btnAddCarrier.setOnClickListener {
            hActivity.homeToStep1()
        }



    }


}