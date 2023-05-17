package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.RequestSignUp
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.data.ResponseSignUp
import retrofit2.Call
<<<<<<< HEAD
import retrofit2.http.Body
=======
import retrofit2.http.GET
>>>>>>> 377faf1ab6f1cd299f49a00430ed2aaf9de8a4aa
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface APIS {

    // 구글 로그인
    @GET("/v1/user/oauth/google")
    fun oauthGoogle(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGoogleSignup>

    //회원가입
    @POST("/v1/user/signUp")
    fun signUp(
        @Body signUp: RequestSignUp
    ) : Call<ResponseSignUp>

}