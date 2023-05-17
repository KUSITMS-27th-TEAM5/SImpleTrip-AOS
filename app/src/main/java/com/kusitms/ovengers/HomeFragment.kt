package com.kusitms.ovengers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.kusitms.ovengers.data.RequestMakeCarrier
import com.kusitms.ovengers.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private lateinit var carrierAdapter: CarrierAdapter
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    val dataSet = ArrayList<RequestMakeCarrier>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        dataSet.add(RequestMakeCarrier("a","d","A","일본 여행"))
//
//        dataSet.add(RequestMakeCarrier("a","d","A","미국 여행"))


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataSet.add(RequestMakeCarrier("a","d","A","일본 여행"))

        dataSet.add(RequestMakeCarrier("a","d","A","미국 여행"))



        carrierAdapter = CarrierAdapter(dataSet)

        binding.carrierRv.adapter = carrierAdapter
        binding.carrierRv.layoutManager = GridLayoutManager(context,2)

        //캐리어 클릭 이벤트
        carrierAdapter.itemClick = object :CarrierAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {
                Toast.makeText(context,dataSet[position].carrierName,Toast.LENGTH_SHORT).show()
            }
        }

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

                    }
                    false
                }
                pop.show()
            }
        }


    }


}