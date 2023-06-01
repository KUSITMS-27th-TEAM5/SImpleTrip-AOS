package com.kusitms.ovengers.data

data class ResponsePostMemo(
    val code: PostMemoCode,
    val `data`: PostMemoData,
    val isSuccess: Boolean,
    val message: String
)

data class PostMemoCode(
    val code: Int,
    val message: String,
    val status: Int
)

data class PostMemoData(
    val imageUrl: String
)