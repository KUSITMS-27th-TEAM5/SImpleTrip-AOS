package com.kusitms.ovengers.data

data class RequestMemoDto<T, U>(
    val carrierId: Int,
    val content: String,
    val ticketId: Int
)