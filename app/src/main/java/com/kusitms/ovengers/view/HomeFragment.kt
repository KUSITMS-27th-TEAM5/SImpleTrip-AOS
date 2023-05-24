package com.kusitms.ovengers.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kusitms.ovengers.*
import com.kusitms.ovengers.data.*
import com.kusitms.ovengers.databinding.FragmentHomeBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var retAPIS: APIS
    private lateinit var carrierAdapter: CarrierAdapter
//    private lateinit var _binding : FragmentHomeBinding
    lateinit var binding : FragmentHomeBinding
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
    private lateinit var viewModel: CarrierViewModel
//    var carrierData = ArrayList<Ddata>()



    fun newInstance() : HomeFragment{
        return HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hActivity = activity as HomeActivity
        hActivity.HideBottomNav(false)
//        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

//        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
//        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
//        getCarrier()


        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)


        //view model
        viewModel = ViewModelProvider(this).get(CarrierViewModel::class.java)


        carrierAdapter = CarrierAdapter()
//

        val recyclerView : RecyclerView = binding.carrierRv


       recyclerView.adapter = carrierAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.carrierList.observe(viewLifecycleOwner){ carrierList->
            carrierAdapter.updateList(carrierList)


            //클릭 시 티켓 보관함 이동
            carrierAdapter.itemClick = object :CarrierAdapter.ItemClick{

                override fun onClick(view: View, position: Int) {

                    val intent = Intent(activity,CarrierInfoActivity::class.java)

                    startActivity(intent)



                }
            }

            //길게 클릭 시 편집 목록
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
                                deleteCarrier(carrierList[position].name)
                            R.id.popup_cancle->
                                Toast.makeText(context,"cancle",Toast.LENGTH_SHORT).show()

                        }
                        false
                    }
                    pop.show()
                }
            }

            //        //캐리어 생성 버튼
        val hActivity = activity as HomeActivity

        binding.btnAddCarrier.setOnClickListener {
            hActivity.homeToStep1()
        }
        }

        val view = binding.root
        return view



//
    }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//        val name = MyApplication.prefs.getString("userName","String")
//
//
//
//        binding.carrierWho.setText("${name} 님의 티켓 캐리어")
//
//
////        getCarrier()
//
////        carrierData.add(Ddata(100,"dd"))
//
//
//
//
////
////            Log.d("carrierdata",carrierData.toString())
////            carrierAdapter = CarrierAdapter(carrierData)
////
////            binding.carrierRv.adapter = carrierAdapter
////            binding.carrierRv.layoutManager = GridLayoutManager(context,2)
//
//
//


//

//
//
//
//    }



    private fun deleteCarrier(name : String){
        val bearerToken = "Bearer $accessToken"
        retAPIS.deleteCarrier(bearerToken, RequestDeleteCarrier(name)).enqueue(object : Callback<ResponseDeleteCarrier> {
            override fun onResponse(call: Call<ResponseDeleteCarrier>, response: Response<ResponseDeleteCarrier>) {
                if (response.isSuccessful) {

                    Log.d("delete Response : ", response.body().toString())




                } else {
                    Log.d("delete Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseDeleteCarrier>, t: Throwable) {
                Log.d("delete Response : ", "Fail 2")
            }
        })
    }

} // 커밋용