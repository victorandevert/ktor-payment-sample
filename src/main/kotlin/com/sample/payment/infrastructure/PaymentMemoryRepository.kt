package com.sample.payment.infrastructure

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import com.sample.payment.domain.ErrorPaymentNotFound
import com.sample.payment.domain.Payment
import com.sample.payment.domain.PaymentRepository

class PaymentMemoryRepository: PaymentRepository {
    private val payments = mutableMapOf(
        "P1234" to Payment("P1234", "120", "visa"),
        "P1235" to Payment("P1235", "99", "mc"),
        "P1236" to Payment("P1236", "76", "amex")
    )

    override fun add(id: String, payment: Payment) {
        payments[id] = payment
    }

    override fun getAll(): List<Payment> = payments.values.toList()

    override fun get(id: String): Either<ErrorPaymentNotFound, Payment> {
        return when{
            payments.any { id == it.key } -> Right(payments.getValue(id))
            else -> Left(ErrorPaymentNotFound())
        }
    }
}