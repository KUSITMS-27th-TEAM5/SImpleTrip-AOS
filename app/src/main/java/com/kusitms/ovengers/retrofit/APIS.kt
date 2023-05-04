package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.ResponseGoogleSignup
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface APIS {

    // 구글 로그인
    @POST("/v1/user/oauth/google")
    fun oauthGoogle(
        @Path("accessToken") accessToken: String
    ) : Call<ResponseGoogleSignup>

}