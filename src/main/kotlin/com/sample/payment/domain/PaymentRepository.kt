package com.sample.payment.domain

import arrow.core.Either

interface PaymentRepository {

    fun getAll (): List<Payment>
    fun get(id: String): Either<ErrorPaymentNotFound, Payment>
}

class ErrorPaymentNotFound