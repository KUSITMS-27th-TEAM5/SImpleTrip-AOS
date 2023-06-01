package com.kusitms.ovengers.data

data class ResponseDeleteCarrier (
    val isSuccess : Boolean,
    val code : Cccode,
    val message : String,
    val data : DDdata

)

data class Cccode (
    val status : Int,
    val code : Int,
    val message : String
)

data class DDdata (

    val id : Int,
    val name : String
)
