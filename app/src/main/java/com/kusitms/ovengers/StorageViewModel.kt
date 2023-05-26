package com.kusitms.ovengers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kusitms.ovengers.data.ResponseStorageCarrier
import com.kusitms.ovengers.data.StorageData
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class StorageViewModel : ViewModel() {
    private lateinit var retAPIS: APIS
    private val _storageList = MutableLiveData<List<StorageData>>()
    val storageList : LiveData<List<StorageData>> = _storageList

    private val TAG = StorageViewModel::class.java.simpleName


    // SharedPreferences 조희
    val accessToken = MyApplication.prefs.getString("accessToken", "token")
    val username = MyApplication.prefs.getString("username", "username")

    init {
        fetchStorageList()
    }

    // Storage 캐리어 조회 API
    private fun fetchStorageList() {
        // Retrofit
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        viewModelScope.launch {
            try {
                retAPIS.getCarrier(bearerToken).enqueue(object : Callback<ResponseStorageCarrier> {
                    override fun onResponse(call: Call<ResponseStorageCarrier>, response: Response<ResponseStorageCarrier>) {
                        if (response.isSuccessful) {
                            _storageList.value = response.body()?.data
                            Log.d(TAG, "getStorage Response: ${response.body()?.data}")
                        } else {
                            Log.d(TAG, "getStorage Error 1 : ${response.errorBody()}")
                        }
                    } override fun onFailure(call: Call<ResponseStorageCarrier>, t: Throwable) {
                        Log.d(TAG, "getStorage Error 2")
                    }
                })
            } catch (e: Exception) {
                Log.d(TAG, "getStorage Error 3 : ${e.message}")
            }
        }
    }
}