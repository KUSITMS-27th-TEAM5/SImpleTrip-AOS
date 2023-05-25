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
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        val hActivity = activity as HomeActivity
        hActivity.HideBottomNav(false)
//        val nickname = MyApplication.prefs.getString("nickName","String")
//
//        binding.carrierWho.setText("${nickname} 님의 티켓 캐리어")
//        binding = FragmentHomeBinding.inflate(inflater,container,false)
//
//
//
//
//
//
//        //view model
//        viewModel = ViewModelProvider(this).get(CarrierViewModel::class.java)
//
//
//        carrierAdapter = CarrierAdapter()
////
//
//        val recyclerView : RecyclerView = binding.carrierRv
//
//
//       recyclerView.adapter = carrierAdapter
//        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
//
//        viewModel.carrierList.observe(viewLifecycleOwner){ carrierList->
//            carrierAdapter.updateList(carrierList)
//
//
//            //클릭 시 티켓 보관함 이동
//            carrierAdapter.itemClick = object :CarrierAdapter.ItemClick{
//
//                override fun onClick(view: View, position: Int) {
//
//                    val intent = Intent(activity,CarrierInfoActivity::class.java)
//
//                    intent.putExtra("id",carrierList[position].id)
//
//
//                    startActivity(intent)
//
//
//
//                }
//            }
//
//
//            //길게 클릭 시 편집 목록
//            carrierAdapter.itemLongClick = object :CarrierAdapter.ItemLongClick{
//                override fun onLongClick(view: View, position: Int) {
//                    // Toast.makeText(context,"long",Toast.LENGTH_SHORT).show()
//
//                    val mActivity = activity as HomeActivity
//
//                    var pop = PopupMenu(context,view)
//                    pop.menuInflater.inflate(R.menu.carrier_popup,pop.menu)
//
//                    pop.setOnMenuItemClickListener { item ->
//                        val editCarrierNameFragment =  EditCarrierNameFragment()
//                        val editDateFragment = EditDateFragment()
//                        val editCountryFragment = EditCountryFragment()
//                        val bundle = Bundle()
//                        bundle.putInt("id",carrierList[position].id)
//                        editCarrierNameFragment.arguments = bundle
//                        editCountryFragment.arguments = bundle
//                        editDateFragment.arguments = bundle
//
//                        when(item.itemId) {
//                            R.id.popup_country->
//
//                                fragmentManager?.beginTransaction()?.apply {
//                                    replace(R.id.Main_Frame, editCountryFragment)
//                                    addToBackStack(null)
//                                    commit()
//                                }
//                            R.id.popup_date->
//                                fragmentManager?.beginTransaction()?.apply {
//                                    replace(R.id.Main_Frame, editDateFragment)
//                                    addToBackStack(null)
//                                    commit()
//                                }
//                            R.id.popup_carrier_name->
//                                fragmentManager?.beginTransaction()?.apply {
//                                    replace(R.id.Main_Frame, editCarrierNameFragment)
//                                    addToBackStack(null)
//                                    commit()
//                                }
//                            R.id.popup_delete_carrier->
//                                deleteCarrier(carrierList[position].name)
//                            R.id.popup_cancle->
//                                Toast.makeText(context,"cancle",Toast.LENGTH_SHORT).show()
//
//                        }
//                        false
//                    }
//                    pop.show()
//                }
//            }
//
//            //        //캐리어 생성 버튼
//        val hActivity = activity as HomeActivity
//
//        binding.btnAddCarrier.setOnClickListener {
//            hActivity.homeToStep1()
//        }
//        }
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        val view = binding.root
        return view

    }



    //캐리어삭제 API
    private fun deleteCarrier(id : Int){
        val bearerToken = "Bearer $accessToken"
        retAPIS.deleteCarrier(bearerToken, RequestDeleteCarrier(id)).enqueue(object : Callback<ResponseDeleteCarrier> {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nickname = MyApplication.prefs.getString("nickName","String")


        binding.carrierWho.setText("${nickname} 님의 티켓 캐리어")


        //알람 이동
        binding.btnAlarm.setOnClickListener {
            val notify = NotifyFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, notify)
                addToBackStack(null)
                commit()
            }
        }

        //마이페이지 이동
        binding.btnMypage.setOnClickListener {
            val myPage = Mypage()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.constraint_layout, myPage)
                addToBackStack(null)
                commit()
            }
        }




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
                    intent.putExtra("id",carrierList[position].id)
                    MyApplication.prefs.setString("id",carrierList[position].id.toString())


                    startActivity(intent)



                }
            }


            //길게 클릭 시 편집 목록
            carrierAdapter.itemLongClick = object :CarrierAdapter.ItemLongClick{
                override fun onLongClick(view: View, position: Int) {
                    // Toast.makeText(context,"long",Toast.LENGTH_SHORT).show()

                    val mActivity = activity as HomeActivity

                    var pop = PopupMenu(context,view)
                    pop.menuInflater.inflate(R.menu.carrier_popup,pop.menu)

                    pop.setOnMenuItemClickListener { item ->
                        val editCarrierNameFragment =  EditCarrierNameFragment()
                        val editDateFragment = EditDateFragment()
                        val editCountryFragment = EditCountryFragment()
                        val bundle = Bundle()
                        bundle.putInt("id",carrierList[position].id)
                        editCarrierNameFragment.arguments = bundle
                        editCountryFragment.arguments = bundle
                        editDateFragment.arguments = bundle

                        when(item.itemId) {
                            R.id.popup_country->

                                fragmentManager?.beginTransaction()?.apply {
                                    replace(R.id.Main_Frame, editCountryFragment)
                                    addToBackStack(null)
                                    commit()
                                }
                            R.id.popup_date->
                                fragmentManager?.beginTransaction()?.apply {
                                    replace(R.id.Main_Frame, editDateFragment)
                                    addToBackStack(null)
                                    commit()
                                }
                            R.id.popup_carrier_name->
                                fragmentManager?.beginTransaction()?.apply {
                                    replace(R.id.Main_Frame, editCarrierNameFragment)
                                    addToBackStack(null)
                                    commit()
                                }
                            R.id.popup_delete_carrier->
                                deleteCarrier(carrierList[position].id)
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
    }

} // 커밋용