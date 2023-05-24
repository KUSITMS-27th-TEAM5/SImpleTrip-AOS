package com.kusitms.ovengers.data

data class ResponseGetTicket(
    val code: TicketCode,
    val `data`: List<TicketData>,
    val isSuccess: Boolean,
    val message: String
)

data class TicketCode(
    val code: Int,
    val message: String,
    val status: Int
)

data class TicketData(
    val id: Int,
    val ticketUrl: String,
    val title: String
)