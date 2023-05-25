package com.kusitms.ovengers.data

data class ResponseEditCarrierCountry(
    val isSuccess : Boolean,
    val code : Coodeee,
    val message : String,
    val data : Daataaa

)



data class Coodeee (
    val status : Int,
    val code : Int,
    val message : String
)

data class Daataaa(
    val id : Int,
    val name : String
)
