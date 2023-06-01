package com.kusitms.ovengers.data

import java.time.LocalDate

data class RequestMakeCarrier(
    val country : String,
    val name : String,
    val startDate : String,
    val endDate : String,

)