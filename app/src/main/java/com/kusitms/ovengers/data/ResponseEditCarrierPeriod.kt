package com.kusitms.ovengers.data

data class ResponseEditCarrierPeriod(
    val isSuccess : Boolean,
    val code : Coodee,
    val message : String,
    val data : Daataa
)

data class Coodee (
    val status : Int,
    val code : Int,
    val message : String
)

data class Daataa(
    val id : Int,
    val name : String
)
