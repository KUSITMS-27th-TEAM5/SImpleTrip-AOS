package com.kusitms.ovengers.data

data class ResponsePostLinkUrl (
    val isSuccess : Boolean,
    val code : Coder,
    val message : String,
    val data : Daaata
)
data class Coder (
    val status : Int,
    val code : Int,
    val message : String
        )
data class Daaata (
    val id : Int,
    val title : String,
    val ticketUrl : String
        )