package com.kusitms.ovengers.data

data class ResponseGetPoint(
    val code: Code,
    val data: Int,
    val isSuccess: Boolean,
    val message: String
)

data class Code(
    val code: Int,
    val message: String,
    val status: Int
)