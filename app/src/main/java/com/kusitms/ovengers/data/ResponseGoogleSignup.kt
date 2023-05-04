package com.kusitms.ovengers.data

data class ResponseGoogleSignup(
    val attribute: Attribute,
    val code: Int,
    val message: String
)

data class Attribute(
    val email: String,
    val pictureUrl: String,
    val userName: String
)