package com.kusitms.ovengers.data

data class ResponseCarrierInfo(
    val code: CarrierInfoCode,
    val `data`: CarrierInfoData,
    val isSuccess: Boolean,
    val message: String
)

data class CarrierInfoCode(
    val code: Int,
    val message: String,
    val status: Int
)

data class CarrierInfoData(
    val endDate: String,
    val name: String,
    val startDate: String
)