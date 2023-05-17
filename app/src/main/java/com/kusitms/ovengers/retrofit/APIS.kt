package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.RequestSignUp
import com.kusitms.ovengers.data.ResponseGoogleSignup
import com.kusitms.ovengers.data.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface APIS {

    // 구글 로그인
    @POST("/v1/user/oauth/google")
    fun oauthGoogle(
        @Path("accessToken") accessToken: String
    ) : Call<ResponseGoogleSignup>

    //회원가입
    @POST("/v1/user/signUp")
    fun signUp(
        @Body signUp: RequestSignUp
    ) : Call<ResponseSignUp>

}