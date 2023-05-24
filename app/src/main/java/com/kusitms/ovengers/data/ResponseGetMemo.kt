package com.kusitms.ovengers.data

data class ResponseGetMemo(
    val code: GetMemoCode,
    val `data`: GetMemoData,
    val isSuccess: Boolean,
    val message: String
)

data class GetMemoData(
    val content: String,
    val imageUrl: String
)

data class GetMemoCode(
    val code: Int,
    val message: String,
    val status: Int
)