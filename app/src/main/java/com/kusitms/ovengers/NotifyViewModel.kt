package com.kusitms.ovengers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusitms.ovengers.data.AlarmData
import com.kusitms.ovengers.data.ResponseAlarms
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotifyViewModel : ViewModel() {
    private lateinit var retAPIS: APIS
    private val _alarm = MutableLiveData<List<AlarmData>>()
    val alarm : LiveData<List<AlarmData>> = _alarm

    private val TAG = NotifyViewModel::class.java.simpleName

    // SharedPreferences 조희
    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    val username = MyApplication.prefs.getString("username", "username")

    init {
        fetchAlarm()
    }

    // Alarm 조회 API
    private fun fetchAlarm() {
        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        viewModelScope.launch {
            try {
                retAPIS.getAlarms(bearerToken).enqueue(object : Callback<ResponseAlarms> {
                    override fun onResponse(call: Call<ResponseAlarms>, response: Response<ResponseAlarms>) {
                        if (response.isSuccessful) {
                            _alarm.value = response.body()?.data
                            Log.d(TAG, "getAlarms Response: ${response.body()?.data}")
                        } else {
                            Log.d(TAG, "getAlarms Error 1 : ${response.errorBody()}")
                        }
                    } override fun onFailure(call: Call<ResponseAlarms>, t: Throwable) {
                        Log.d(TAG, "getAlarms Error 2")
                    }
                })
            } catch (e: Exception) {
                Log.d(TAG, "getAlarms Error 3 : ${e.message}")
            }
        }
    }
}