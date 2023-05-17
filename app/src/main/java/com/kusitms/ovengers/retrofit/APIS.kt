package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.RequestSignUp
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.data.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
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
        @Header("Authorization") Authorization: String,
        @Body signUp: RequestSignUp
    ) : Call<ResponseSignUp>

}