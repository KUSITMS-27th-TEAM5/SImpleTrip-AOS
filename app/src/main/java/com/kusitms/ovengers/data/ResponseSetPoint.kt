package com.kusitms.ovengers.data

data class ResponseSetPoint(
    val code: String,
    val `data`: Int,
    val isSuccess: Boolean,
    val message: String,
    val page: SetPage,
)

data class SetPage(
    val number: Int,
    val size: Int,
    val totalCount: Int
)