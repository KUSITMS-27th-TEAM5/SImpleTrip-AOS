package com.kusitms.ovengers.data

data class ResponseGetCarrier (
    val isSuccess : Boolean,
    val code : Ccode,
    val message : String,
    val data : ArrayList<Ddata>

)

data class Ccode (
    val status : Int,
    val code : Int,
    val message : String
        )

data class Ddata (

    val id : Int,
    val name : String
        )