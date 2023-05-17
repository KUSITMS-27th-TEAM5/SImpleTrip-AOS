package com.kusitms.ovengers.data

data class ResponseGoogleSignup(
    val attribute: Attribute,
    val code: Int,
    val isSuccess: Boolean,
    val message: String
)

data class Attribute(
    val accessToken: String,
    val email: String,
    val pictureUrl: String,
    val refreshToken: Any,
    val userName: String
)