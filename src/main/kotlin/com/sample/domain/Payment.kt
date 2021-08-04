package com.sample.domain

import kotlinx.serialization.Serializable

@Serializable
data class Payment(val id: String, val amount: String, val paymentMethod: String)

val storage = mutableListOf<Payment>()