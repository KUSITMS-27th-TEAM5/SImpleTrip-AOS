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
import com.google.api.client.auth.oauth2.BearerToken
import android.content.Context
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.kusitms.ovengers.MyApplication.Companion.prefs
import com.kusitms.ovengers.data.ResponseGetPoint
import com.kusitms.ovengers.databinding.FragmentMypageBinding
import com.kusitms.ovengers.databinding.FragmentStoreDetailBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class Mypage : Fragment() {

    val REQUEST_PERMISSION = 100
    val REQUEST_GALLERY = 200

    private lateinit var retAPIS: APIS
    lateinit var binding: FragmentMypageBinding



    override fun onCreateView( // Fragment의 UI를 생성하고 반환하는 메소드, xml 레이아웃 파일 인플레이트, view 객체 생성 및 초기화
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        binding = FragmentMypageBinding.inflate(inflater, container, false)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        // SharedPreferences 조희
        // val accessToken = MyApplication.prefs.getString("accessToken", "token")
        val accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJza2Rrc21zMTIzQGdtYWlsLmNvbSIsImlhdCI6MTY4NDE2NjcxNSwiZXhwIjoxNjg2NzU4NzE1fQ.GHxv56XM0Cfst4JyCI5cXf5NLh82aGwbjKcKAV6-M_lijRVve_O-CcTlwvUsfPsTQFZ8-t_la4nHehIlryDTiQ"
        val username = MyApplication.prefs.getString("username", "username")


        binding.name.text = username.toString()

        // 포인트 조회 함수 호출
        getPoint(accessToken)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //onCreateView 이후에 호출되는 메서드로 view가 생성되고 초기화된 후에 호출됨. View에 대한 작업 수행
        super.onViewCreated(view, savedInstanceState)

        val pointTextView = view.findViewById<TextView>(R.id.mycash)



        // beforeBtn을 누르면 이전 화면으로 돌아가게 설정 -> 아마 navigation xml 파일에서 프래그먼트 액션 대상 설정 필요
        val beforeBtn = view.findViewById<ImageView>(R.id.beforeBtn)
        beforeBtn.setOnClickListener {
            findNavController().navigateUp()
            Log.d("beforebtn pushed", "이전 화면 눌림")
        }

        // next01 이미지뷰를 클릭하면 다음 프래그먼트로 전환 -> 아마 navigation xml 파일에서 프래그먼트 액션 대상 설정 필요
        val imageView01 = view.findViewById<ImageView>(R.id.next01)
        imageView01.setOnClickListener {
//            findNavController().navigate(R.id.action_mypage_to_notdeveloped~)
        }

        // next02 이미지뷰를 클릭하면 다음 프래그먼트로 전환 -> 아마 navigation xml 파일에서 프래그먼트 액션 대상 설정 필요
        val imageView02 = view.findViewById<ImageView>(R.id.next02)
        imageView02.setOnClickListener {
//            findNavController().navigate(R.id.action_mypage_to_notdeveloped~)
        }

        // next01 이미지뷰를 클릭하면 다음 프래그먼트로 전환 -> 아마 navigation xml 파일에서 프래그먼트 액션 대상 설정 필요
        val imageView03 = view.findViewById<ImageView>(R.id.next03)
        imageView03.setOnClickListener {
//            findNavController().navigate(R.id.action_mypage_to_notdeveloped~)
        }


        //xml에 있는 카메라 버튼 누르면 openGallery 실행
        val buttonCamera = view.findViewById<ImageButton>(R.id.buttonCamera)
        buttonCamera.setOnClickListener { openGallery() }
        Log.d("Camerabtn pushed", "카메라 버튼 눌림")


        // xml에 있는 연필 버튼 누르면 닉네임 수정 함수 실행
        val editNickname = view.findViewById<ImageView>(R.id.editBtn)
        editNickname.setOnClickListener { nicknameChange() }


        // xml에 있는 로그아웃 텍스트 누르면 로그아웃 함수 실행
        val logoutbtn = view.findViewById<TextView>(R.id.logout)
        logoutbtn.setOnClickListener { logoutApiRequest() }
        Log.d("Logoutbtn pushed", "로그아웃 텍스트 눌림")
    }



    //닉네임 수정하는 함수
    private fun nicknameChange() {
        TODO("Not yet implemented")
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

    // 포인트 조회 함수
    private fun getPoint(accessToken: String) {
        // Bearer 추가
        val bearerToken = "Bearer $accessToken"
        retAPIS.getPoint(bearerToken).enqueue(object : Callback<ResponseGetPoint> {  //restAPIS에 있는 getPoint 함수를 쓸 건데 눌러보면 @header 이리 되어 있음
            override fun onResponse(call: Call<ResponseGetPoint>, response: Response<ResponseGetPoint>) {
                // 오류 코드
                var code: String? = response.body()?.code.toString()
                if (response.isSuccessful) {
                    val point : Int? = response.body()?.data
                    Log.d("GetPoint Response Message : ", response.message())
                    Log.d("Point Response : ", point.toString())

                    // 잔여 포인트 화면에 띄우기
                    binding.mycash.text = point.toString()

                } else {
                    if (code == "-5001") { // 토큰 만료
                        // newToken(bearerToken) // 토큰 재발급 함수 호출
                    }
                    Log.d("Oauth Login Response : ", "Fail 1")
                }
            } override fun onFailure(call: Call<ResponseGetPoint>, t: Throwable) {
                Log.d("Oauth Login Response : ", "Fail 2")
            }
        })
    }

    //로그아웃 함수
    private fun logoutApiRequest() {
        // 로그아웃 처리를 수행하는 코드 작성 -> 필요가 없어진 상태..!?
//        val bearerToken = "Bearer $accessToken"
//        retAPIS.Logout(bearerToken).enqueue(object : Callback<ResponseGetPoint>{
//
//        }
//            // 예를 들어, SharedPreferences에서 저장된 토큰을 삭제하거나 세션을 종료하는 등의 작업을 수행할 수 있다.
//
//            // 예시: SharedPreferences에서 토큰 삭제
//            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//        prefs.edit().remove("accessToken").apply()

        Log.d("Logout", "토큰 삭제 완료!")

        // 로그인 화면으로 이동
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // 현재 액티비티를 종료하여 이전 화면으로 돌아갈 수 있도록 하기

        Log.d("move to Login", "로그인 화면으로 전환 완료")
    }





}



