package com.sample.payment.infrastructure

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.sample.payment.domain.ErrorPaymentNotFound
import com.sample.payment.domain.Payment
import com.sample.payment.domain.PaymentRepository

class PaymentMemoryRepository: PaymentRepository {
    private val payments = listOf(
        Payment("P1234", "120", "visa"),
        Payment("P1235", "99", "mc"),
        Payment("P1236", "76", "amex")
    )

    override fun getAll(): List<Payment> = payments

    override fun get(id: String): Either<ErrorPaymentNotFound, Payment> {
        return when{
            payments.any { id == it.id } -> Right(payments.first { id == it.id })
            else -> Left(ErrorPaymentNotFound())
        }
    }
}