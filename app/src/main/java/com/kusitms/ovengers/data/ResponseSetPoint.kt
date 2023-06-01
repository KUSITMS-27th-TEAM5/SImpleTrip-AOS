package com.kusitms.ovengers.data

data class ResponseSetPoint(
    val code: PointCode,
    val `data`: Int,
    val isSuccess: Boolean,
    val message: String
)

data class PointCode(
    val code: Int,
    val message: String,
    val status: Int
)