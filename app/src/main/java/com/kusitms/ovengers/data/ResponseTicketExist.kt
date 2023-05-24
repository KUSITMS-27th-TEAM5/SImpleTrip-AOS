package com.kusitms.ovengers.data

data class ResponseTicketExist(
    val code: TicketExistCode,
    val `data`: TicketExistData,
    val isSuccess: Boolean,
    val message: String
)

data class TicketExistCode(
    val code: Int,
    val message: String,
    val status: Int
)

class TicketExistData