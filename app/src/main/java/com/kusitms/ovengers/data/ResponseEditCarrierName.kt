package com.kusitms.ovengers.data

data class ResponseEditCarrierName (
    val isSuccess : Boolean,
    val code : Coode,
    val message : String,
    val data : Daata

)



data class Coode (
    val status : Int,
    val code : Int,
    val message : String
)

data class Daata(
    val id : Int,
    val name : String
)





