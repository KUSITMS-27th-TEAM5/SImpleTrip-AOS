package com.kusitms.ovengers.data

data class ResponseAlarms(
    val code: AlarmCode,
    val `data`: List<AlarmData>,
    val isSuccess: Boolean,
    val message: String
)

data class AlarmCode(
    val code: Int,
    val message: String,
    val status: Int
)

data class AlarmData(
    val id: Int,
    val message: String,
    val createdDate : String
)