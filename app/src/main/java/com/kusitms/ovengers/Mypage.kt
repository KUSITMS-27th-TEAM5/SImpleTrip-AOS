@file:Suppress("UNREACHABLE_CODE")

package com.kusitms.ovengers

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import com.kusitms.ovengers.databinding.ActivityLoginMoreInfoBinding
import android.Manifest
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.kusitms.ovengers.R
import retrofit2.http.Url

class Mypage : Fragment() {

    val REQUEST_PERMISSION = 100
    val REQUEST_GALLERY = 200


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // beforeBtn을 누르면 이전 화면으로 돌아가게 설정
        val beforeBtn = view.findViewById<ImageView>(R.id.beforeBtn)
        beforeBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        // next01 이미지뷰를 클릭하면 다음 프래그먼트로 전환
//        view.findViewById<ImageView>(R.id.next01).setOnClickListener {
//            it.findFragment<>()
//        }

        val buttonCamera = view.findViewById<ImageButton>(R.id.buttonCamera)
        buttonCamera.setOnClickListener { openGallery() }
    }

    //갤러리 열기 위한 함수
    private fun openGallery() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_GALLERY)
        }
    }

    //갤러리 액티비티에서 결과를 받는 함수
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            val imageView = requireView().findViewById<ImageView>(R.id.profile)
            imageView.setImageURI(imageUri)
        }
    }

    //권한 요청 결과를 받는 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        }
    }
}


//@file:Suppress("UNREACHABLE_CODE")
//
//package com.kusitms.ovengers
//
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.ImageView
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import android.Manifest
//import androidx.navigation.fragment.findNavController
//import com.kusitms.ovengers.databinding.FragmentMypageBinding
//import com.kusitms.ovengers.retrofit.APIS
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import com.kusitms.ovengers.view.NotDeveloped
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class Mypage : Fragment() {
//
//    val REQUEST_PERMISSION = 100
//    val REQUEST_GALLERY = 200
//    private lateinit var retAPIS: APIS
//
//
//    lateinit var binding: FragmentMypageBinding
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentMypageBinding.inflate(inflater)
////        val view = inflater.inflate(R.layout.fragment_mypage, container, false)
////        return view
//        return binding.root
//
//    }
//
//    fun onNext01Click(view: View) {
//        // fragment 전환 코드 작성 mypage (스토어 예매내역) -> 개발 아직 안된 페이지
//        val fragment = NotDeveloped()
//        val fragmentManager = requireActivity().supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.next01, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        // beforeBtn을 누르면 이전 화면으로 돌아가게 설정
//        val beforeBtn = view.findViewById<ImageView>(R.id.beforeBtn)
//        beforeBtn.setOnClickListener {
//            findNavController().navigateUp()
//        }
//
//
//
//        val buttonCamera = view.findViewById<ImageButton>(R.id.buttonCamera)
//        buttonCamera.setOnClickListener { openGallery() }
//    }
//
//    private fun fetchPoints() {
//        // API 호출 및 결과 처리 (포인트 조회??????) GPT에게 물어본 건데 헤헷 모르겠
//        val apis = APIS.create() // MyApiService는 레트로핏 인터페이스와 클라이언트 설정을 포함한 클래스입니다.
//        val call = apis.getPoints()
//
//        call.enqueue(object : Callback<PointsResponse> {
//            override fun onResponse(call: Call<PointsResponse>, response: Response<PointsResponse>) {
//                if (response.isSuccessful) {
//                    val points = response.body()?.points
//                    // 포인트 데이터를 사용하여 필요한 작업 수행
//                } else {
//                    // API 요청이 실패한 경우에 대한 처리
//                }
//            }
//
//            override fun onFailure(call: Call<PointsResponse>, t: Throwable) {
//                // API 호출 실패에 대한 처리
//            }
//        })
//    }
//
//    //갤러리 열기 위한 함수
//    private fun openGallery() {
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                REQUEST_PERMISSION
//            )
//        } else {
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(intent, REQUEST_GALLERY)
//        }
//    }
//
//    //갤러리 액티비티에서 결과를 받는 함수
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
//            val imageUri: Uri? = data?.data
//            val imageView = requireView().findViewById<ImageView>(R.id.profile)
//            imageView.setImageURI(imageUri)
//        }
//    }
//
//    //권한 요청 결과를 받는 함수
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            openGallery()
//        }
//    }
//
//}



