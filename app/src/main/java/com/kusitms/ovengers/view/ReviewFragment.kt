package com.kusitms.ovengers.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.kusitms.ovengers.MyApplication
import com.kusitms.ovengers.R
import com.kusitms.ovengers.data.ResponsePostMemo
import com.kusitms.ovengers.databinding.FragmentReviewBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import kotlin.collections.MutableMap

class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private lateinit var retAPIS: APIS

    private val REQ_GALLERY = 100
    private val PERMISSION_REQUEST_CODE = 300
    private var path: String = ""

    private var accessToken = ""
    private var username = ""
    private var carrierId = ""
    private var ticketId = ""
    private var ticketTitle = ""
    private var text = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        accessToken =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        username = MyApplication.prefs.getString("username", "username")!!
        carrierId = MyApplication.prefs.getString("carrierId", "1")!!
        ticketId = MyApplication.prefs.getString("ticketId", "1")!!
        ticketTitle = MyApplication.prefs.getString("ticketTitle", "title")!!

        // 티켓 제목 설정
        binding.name.text = ticketTitle.toString()

        binding.img.setOnClickListener {
            openGallery()
        }

//        binding.save.setOnClickListener{
//            fragmentManager?.beginTransaction()?.apply {
////                replace(R.id.constraint_layout, storageFragment)
////                addToBackStack(null)
////                commit()
//                remove(this@ReviewFragment).commit()
//            }
//        }

        binding.btnBack.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
//
                remove(this@ReviewFragment).commit()
            }
        }
        binding.save.setOnClickListener {
//            val editText: String = binding.text.text.toString()
//            text = editText
//            Log.d("PostMemo Text : ", text)
//            if (ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                //postMemo(accessToken, carrierId, ticketId, text)
//            } else {
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                    PERMISSION_REQUEST_CODE
//                )
//            }

            fragmentManager?.beginTransaction()?.apply {
//
                remove(this@ReviewFragment).commit()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            when (requestCode) {
                REQ_GALLERY -> {
                    data?.data?.let { uri ->
                        binding.img.setImageURI(uri)
                        Log.d("URI: ", uri.toString())
                        path = getImagePath(uri).toString()
                        Log.d("Image Path: ", path)
                    }
                }
            }
        }
    }

    private fun getImagePath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }
        return null
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_GALLERY)
    }

    /*

    private fun postMemo(accessToken: String, carrierId: String, ticketId: String, text: String) {
        val bearerToken = "Bearer $accessToken"

        val carrierIdRequestBody: RequestBody = carrierId.toRequestBody("text/plain".toMediaTypeOrNull())
        val ticketIdRequestBody: RequestBody = ticketId.toRequestBody("text/plain".toMediaTypeOrNull())
        val contentRequestBody: RequestBody = text.toRequestBody("text/plain".toMediaTypeOrNull())

        val map: MutableMap<String, RequestBody> = mutableMapOf()
        map["carrierId"] = carrierIdRequestBody
        map["ticketId"] = ticketIdRequestBody
        map["content"] = contentRequestBody

        val imageFile = File(path)
        val requestFile: RequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val part: MultipartBody.Part = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)

        retAPIS.postMemo(bearerToken, part, RequestBody(carrierId, text, ticketId)).enqueue(object : Callback<ResponsePostMemo> {
            override fun onResponse(call: Call<ResponsePostMemo>, response: Response<ResponsePostMemo>) {
                if (response.isSuccessful) {
                    Log.d("ResponsePostMemo: ", "Success")
                    Log.d("ResponsePostMemo: ", response.message().toString())
                } else {
                    Log.d("ResponseGetMemo: ", "Error 1")
                }
            }

            override fun onFailure(call: Call<ResponsePostMemo>, t: Throwable) {
                Log.d("ResponseGetMemo: ", "Error 2")
            }
        })
    }
    */
     */

}