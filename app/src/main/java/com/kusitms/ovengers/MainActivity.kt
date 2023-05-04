package com.kusitms.ovengers

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.databinding.ActivityMainBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var mGoogleSignInClient : GoogleSignInClient? = null
    lateinit var retService: APIS

    var id : String = ""
    var familyName : String = ""
    var givenName : String = ""
    var email : String = ""
    var token : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        retService = RetrofitInstance.retrofitInstance().create(APIS::class.java)

        setContentView(binding.root)

        //1003027264134-gs0cf4qk6j2n4m2hsa9p7pds02g43ov8.apps.googleusercontent.com
        val id = "AIzaSyDS4IplJfUGFU7O1Tq9g20dlj99liQRqhw"
        // 구글 로그인 인스턴스 생성
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //.requestIdToken(id)
            .requestEmail()
            .build()

        // 구글 로그인 런처
        var googleLoginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == -1) {
                val data = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                getGoogleInfo(task)
            }
        }

        //gso 클라이언트 생성
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // 구글 로그인 버튼 누를 시 로그인 진행
        binding.btnLogin.setOnClickListener {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            googleLoginLauncher.launch(signInIntent)
        }

    }

    // 구글 로그인 실행
//    fun googleLogin() {
//        val signInIntent = mGoogleSignInClient!!.signInIntent
//        googleLoginLauncher.launch(signInIntent)
//    }

    // 구글 로그인 결과 수신
    fun getGoogleInfo(completedTask: Task<GoogleSignInAccount>) {
        try {
            val TAG = "구글 로그인 결과"
            val account = completedTask.getResult(ApiException::class.java)
            Log.d(TAG, account.id!!)
            Log.d(TAG, account.familyName!!)
            Log.d(TAG, account.givenName!!)
            Log.d(TAG, account.displayName!!)
            Log.d(TAG, account.email!!)
            Log.d(TAG, account.photoUrl.toString()!!)
            // Log.d(TAG, account.idToken!!)

        }
        catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun oauthGoogle(token: String){
        val TAG = "OauthGoogle 결과"
        retService.oauthGoogle(token).enqueue(object :
            Callback<ResponseGoogleSignup> {
            override fun onResponse(call: Call<ResponseGoogleSignup>, response: Response<ResponseGoogleSignup>) {
                if (response.isSuccessful) {
                    val email = response.body()?.attribute?.email
                } else {
                    Log.d(TAG, "실패했어요 :<")
                }
            }
            override fun onFailure(call: Call<ResponseGoogleSignup>, t: Throwable) {
                Log.d(t.toString(), "error: ${t.toString()}")
            }
        })
    }
}

