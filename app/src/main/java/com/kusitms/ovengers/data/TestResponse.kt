package com.kusitms.ovengers.data

data class TestResponse(
    val attribute: Attribute,
    val code: Int,
    val isSuccess: Boolean,
    val message: String
)

data class Attribute2(
    val accessToken: String,
    val refreshToken: String
)