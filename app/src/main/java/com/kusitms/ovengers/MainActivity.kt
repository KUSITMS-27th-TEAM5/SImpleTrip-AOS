package com.kusitms.ovengers

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.databinding.ActivityMainBinding
import com.kusitms.ovengers.retrofit.APIS
import com.kusitms.ovengers.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private var GOOGLE_LOGIN_CODE = 9001
    }

    private var auth : FirebaseAuth? = null
    private var googleSignInClient : GoogleSignInClient? = null
    lateinit var binding: ActivityMainBinding
    private lateinit var retAPIS: APIS


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 레트로핏
        retAPIS = RetrofitInstance.retrofitInstance().create(APIS::class.java)
        // 파이어베이스 인스턴스 생성
        auth = FirebaseAuth.getInstance()

        // 구글 로그인 버튼
        binding.btnLogin.setOnClickListener {
            googleLogin()
        }

        // Config SignIn
        val serverClientId = "69907779161-35tiu2ekpd8sqm02pa7ueh4g4pb3eggr.apps.googleusercontent.com"
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(serverClientId)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
    }

    // 구글 로그인 함수
    fun googleLogin(){
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent!!,GOOGLE_LOGIN_CODE)
    }

    // Firebase로 AuthGoogle 로그인 하기
    fun firebaseAuthWithGoogle(account : GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(account?.idToken,null)
        Log.d(TAG, "구글 idToken : $credential")

        // 레트로핏 동작 함수에 idToken 값 넘기기
        // oauthGoogle(credential.toString())

        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"idToken 받아오기 성공!",Toast.LENGTH_SHORT).show()
                }else{
                    // 틀렸을 때
                    Toast.makeText(this,task.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

    // 구글 API 응답값 받아오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_LOGIN_CODE){
            var result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)!!
            if(result.isSuccess) {
                var accout = result.signInAccount
                firebaseAuthWithGoogle(accout)
                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }
    }

    // idToken 값 서버 전송
    private fun oauthGoogle(idToken: String) {
        // Bearer 추가
        val bearerToken = "Bearer $idToken"
        retAPIS.oauthGoogle(bearerToken).enqueue(object : Callback<ResponseGoogleSignup> {
            override fun onResponse(call: Call<ResponseGoogleSignup>, response: Response<ResponseGoogleSignup>) {
                if (response.isSuccessful) {
                    val responseCode = response.body()?.code
                    val responseMessage = response.body()?.message
                    Log.d("Oauth Login Response : ", responseCode.toString() + responseMessage.toString())

                    val accessToken = response.body()?.attribute?.accessToken
                    val refreshToken = response.body()?.attribute?.refreshToken
                    Log.d("AccessToken Response : ", accessToken.toString())

                    // 페이지 이동
                    move(accessToken.toString())
                } else {

                }
            } override fun onFailure(call: Call<ResponseGoogleSignup>, t: Throwable) {
                Log.d("Oauth Login Response : ", "Fail")
            }
        })
    }

    private fun move(accessToken : String){
        val intent = Intent(this, LoginMoreInfo::class.java)
        intent.putExtra("accessToken", accessToken)
        finish()
        startActivity(intent)
    }
}