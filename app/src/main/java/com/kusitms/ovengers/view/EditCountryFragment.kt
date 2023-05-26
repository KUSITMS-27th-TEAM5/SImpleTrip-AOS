package com.kusitms.ovengers.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kusitms.ovengers.DestinationAdapter
import com.kusitms.ovengers.HomeActivity
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.*
import com.kusitms.ovengers.databinding.FragmentEditCountryBinding
import com.kusitms.ovengers.databinding.FragmentHomeBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditCountryFragment : Fragment() {

    private lateinit var destinationAdapter : DestinationAdapter
    private val dataSet = ArrayList<Destination>()

    var destination = ""

    private lateinit var retAPIS: APIS
    lateinit var binding : FragmentEditCountryBinding
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"


    fun newInstance() : EditCountryFragment{
        return EditCountryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)



        binding = FragmentEditCountryBinding.inflate(inflater,container,false)
        return binding.root
    }


    //캐리어 국가 수정 API
    private fun editCarrierCountry(id : Int, country : String){
        val bearerToken = "Bearer $accessToken"
        retAPIS.editCarrierCountry(bearerToken, RequestEditCarrierCountry(id, country)).enqueue(object :
            Callback<ResponseEditCarrierCountry> {
            override fun onResponse(call: Call<ResponseEditCarrierCountry>, response: Response<ResponseEditCarrierCountry>) {
                if (response.isSuccessful) {

                    Log.d("editCarrierCountry Response : ", response.body().toString())




                } else {
                    Log.d("editCarrierCountry Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseEditCarrierCountry>, t: Throwable) {
                Log.d("editCarrierCountry Response : ", "Fail 2")
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt("id")
        Log.d("id_country",id.toString())

        binding.textKorea.setOnClickListener{
            binding.textKorea.setBackgroundResource(R.drawable.border_underline)
            binding.textWorld.setBackgroundColor(Color.parseColor("#ffffff"))
            dataSet.clear()

            dataSet.add(Destination("서울",R.drawable.seoul))
            dataSet.add(Destination("부산",R.drawable.busan))
            dataSet.add(Destination("강릉",R.drawable.gangneung))
            dataSet.add(Destination("가평",R.drawable.gapyeong))
            dataSet.add(Destination("경주",R.drawable.gyeongju))
            dataSet.add(Destination("속초",R.drawable.sokcho))
            dataSet.add(Destination("전주",R.drawable.jeonju))
            dataSet.add(Destination("여수",R.drawable.yeosu))
            destinationAdapter = DestinationAdapter(dataSet)
            binding.destinationRv.adapter = destinationAdapter
            binding.destinationRv.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)


            destinationAdapter.itemClick = object : DestinationAdapter.ItemClick{


                override fun onClick(view: View, position: Int) {
                    destination = dataSet[position].destination
                    Log.d("destination",destination)
                    Toast.makeText(context,"${destination} 선택", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.textWorld.setOnClickListener{
            binding.textWorld.setBackgroundResource(R.drawable.border_underline)
            binding.textKorea.setBackgroundColor(Color.parseColor("#ffffff"))
            dataSet.clear()

            dataSet.add(Destination("일본",R.drawable.japan))
            dataSet.add(Destination("중국",R.drawable.china))
            dataSet.add(Destination("미국",R.drawable.america))
            dataSet.add(Destination("대만",R.drawable.taiwan))
            dataSet.add(Destination("홍콩",R.drawable.hongkong))
            dataSet.add(Destination("태국",R.drawable.thailand))
            dataSet.add(Destination("영국",R.drawable.uk))
            dataSet.add(Destination("프랑스",R.drawable.france))
            destinationAdapter = DestinationAdapter(dataSet)
            binding.destinationRv.adapter = destinationAdapter
            binding.destinationRv.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,false)


            destinationAdapter.itemClick = object : DestinationAdapter.ItemClick{


                override fun onClick(view: View, position: Int) {
                    destination = dataSet[position].destination
                    Log.d("destination",destination)
                    Toast.makeText(context,"${destination} 선택", Toast.LENGTH_SHORT).show()
                }
            }

        }


        //수정 완료
        binding.editFinish.setOnClickListener {
            if (destination == "") {
                Toast.makeText(context, "여행지를 선택해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                MyApplication.prefs.setString("destination", destination)
            }

            editCarrierCountry(id,destination)

            //뒤로가기
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
            val hActivity = activity as HomeActivity
            hActivity.HideBottomNav(false)

        }
    }


}