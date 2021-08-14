package com.sample.payment.infrastructure

import com.sample.payment.domain.Payment
import com.sample.payment.domain.PaymentRepository

class PaymentMemoryRepository: PaymentRepository {

    override fun add(payment: Payment) {
        TODO("Not yet implemented")
    }

    override fun get(): List<Payment> {
        return listOf(
            Payment("P1234", "120", "visa"),
            Payment("P1235", "99", "mc"),
            Payment("P1236", "76", "amex")
        )
    }
}