package com.kusitms.ovengers.data

data class SelectMyProfile(
    val resultCode : Int,
    val resultMessage : String
)

data class result(
    val name : String,
    val gender : String,
    val nickname : String,
    val email : String,
    val image : String,
    val birth : String,
    val picture : String,
    val cash : Int
)