package com.sample.payment.domain

import arrow.core.Either

interface PaymentRepository {

    fun add(id: String, payment: Payment)
    fun getAll (): List<Payment>
    fun get(id: String): Either<ErrorPaymentNotFound, Payment>
}

class ErrorPaymentNotFound