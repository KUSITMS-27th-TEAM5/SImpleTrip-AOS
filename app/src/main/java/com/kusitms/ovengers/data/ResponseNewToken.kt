package com.kusitms.ovengers.data

data class ResponseNewToken(
    val attribute: AttributeToken,
    val code: Int,
    val isSuccess: Boolean,
    val message: String
)

data class AttributeToken(
    val accessToken: String,
    val refreshToken: Any
)