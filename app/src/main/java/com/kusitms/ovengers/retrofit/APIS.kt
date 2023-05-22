package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIS {

    // 회원가입 | 구글 로그인 | 유정
    @GET("/v1/user/oauth/google")
    fun oauthGoogle(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGoogleSignup>

    // 회원가입 | 추가 정보 수집 | 승균
    @POST("/v1/user/signUp")
    fun signUp(
        @Header("Authorization") Authorization: String,
        @Body signUp: RequestSignUp
    ) : Call<ResponseSignUp>

    // 스토어 | 포인트 조회 | 유정
    @GET("/v1/myStore/selectPoint")
    fun getPoint(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGetPoint>

    // 스토어 | 포인트 차감 | 유정
    @GET("/v1/myStore/updatePoint")
    fun setPoint(
        @Header("Authorization") Authorization: String,
        @Path("point") point: Int
    ) : Call<ResponseSetPoint>

    // accessToken 재발급 | 유정
    @GET("/v1/user/reissue")
    fun newToken(
        @Header("Authorization") Authorization: String,
    ) : Call<ResponseNewToken>

    // 나의 프로필 이미지 수정 | 시연
    @PUT("/mypage/updateMyProfileImage")
    fun upadateProfileImg(

    ) : Call<updateProfileImage>

    // 나의 닉네임 수정
    @PUT("/mypage/updateMyNickname")
    fun updateNickname(

    ) : Call<RequestUpdateNickname>

    // 나의 프로필 조회
    @GET("/mypage/selectMyProfile")
    fun selecetMyProfile(

    ) : Call<SelectMyProfile>

}