package com.kusitms.ovengers.data

data class ResponseGetPoint(
    val code: String,
    val `data`: Int,
    val isSuccess: Boolean,
    val message: String,
    val page: Page
)
data class Page(
    val number: Int,
    val size: Int,
    val totalCount: Int
)