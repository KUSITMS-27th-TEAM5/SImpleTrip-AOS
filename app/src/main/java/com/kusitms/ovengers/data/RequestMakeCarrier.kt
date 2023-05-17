package com.kusitms.ovengers.data

data class RequestMakeCarrier(
    val country : String,
    val departureDate : String,
    val arrivalDate : String,
    val carrierName : String
)