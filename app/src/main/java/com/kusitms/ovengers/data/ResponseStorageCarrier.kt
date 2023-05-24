package com.kusitms.ovengers.data

data class ResponseStorageCarrier(
    val code: Code,
    val `data`: List<StorageData>,
    val isSuccess: Boolean,
    val message: String
)
data class StorageData(
    val id: Int,
    val name: String
)

data class StorageCode(
    val code: Int,
    val message: String,
    val status: Int
)