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
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.play.core.integrity.i
import com.kusitms.ovengers.*
import com.kusitms.ovengers.data.*
import com.kusitms.ovengers.databinding.FragmentHomeBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate


class HomeFragment : Fragment() {
    private lateinit var retAPIS: APIS

    private lateinit var carrierAdapter: CarrierAdapter
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"

    var carrierData = ArrayList<Ddata>()



    fun newInstance() : HomeFragment{
        return HomeFragment()
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        getCarrier()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val hActivity = activity as HomeActivity
        hActivity.HideBottomNav(false)
//        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        getCarrier()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
//        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
//        getCarrier()
        val view = binding.root
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val name = MyApplication.prefs.getString("userName","String")



        binding.carrierWho.setText("${name} 님의 티켓 캐리어")


//        getCarrier()

        carrierData.add(Ddata(100,"dd"))





            Log.d("carrierdata",carrierData.toString())
            carrierAdapter = CarrierAdapter(carrierData)

            binding.carrierRv.adapter = carrierAdapter
            binding.carrierRv.layoutManager = GridLayoutManager(context,2)
            carrierAdapter.notifyDataSetChanged()


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
                            deleteCarrier(carrierData[position].name)
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

    private fun getCarrier(){
        val bearerToken = "Bearer $accessToken"
        retAPIS.getCarrier(bearerToken).enqueue(object : Callback<ResponseGetCarrier> {
            override fun onResponse(call: Call<ResponseGetCarrier>, response: Response<ResponseGetCarrier>) {
                if (response.isSuccessful) {

                    Log.d("getCarrier Response : ", "success")



                   carrierData =  response.body()!!.data

                    carrierAdapter.notifyDataSetChanged()
                    Log.d("carrierdata",carrierData.toString())



                } else {
                    Log.d("getCarrier Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseGetCarrier>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

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

}