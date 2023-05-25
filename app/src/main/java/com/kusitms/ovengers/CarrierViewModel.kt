package com.kusitms.ovengers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusitms.ovengers.data.Ddata
import com.kusitms.ovengers.data.ResponseGetCarrier
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarrierViewModel : ViewModel() {
    private lateinit var retAPIS: APIS
    private val _carrierList = MutableLiveData<List<Ddata>>()
    val carrierList : LiveData<List<Ddata>> = _carrierList


    private val TAG = CarrierViewModel::class.java.simpleName
    // SharedPreferences 조희
    // val accessToken = MyApplication.prefs.getString("accessToken", "token")
    val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"

    init {
        fetchCarrierList()
    }

    //Carrier 조회 API
    private fun fetchCarrierList() {
        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        viewModelScope.launch {
            try {
                retAPIS.getCarrierList(bearerToken).enqueue(object : Callback<ResponseGetCarrier> {
            override fun onResponse(call: Call<ResponseGetCarrier>, response: Response<ResponseGetCarrier>) {
                if (response.isSuccessful) {


                    Log.d("getCarrier Response : ", "success")


                   _carrierList.value = response.body()?.data


                    Log.d("carrierdata",response.body()?.data.toString())



                } else {
                    Log.d("getCarrier Response : ", "Fail 1")
                }
            }
            override fun onFailure(call: Call<ResponseGetCarrier>, t: Throwable) {
                Log.d("getCarrier Response : ", "Fail 2")
            }
        })

            } catch (e:Exception){
                Log.d("getCarrier Response : ", "Fail 3")
            }
        }
    }
}