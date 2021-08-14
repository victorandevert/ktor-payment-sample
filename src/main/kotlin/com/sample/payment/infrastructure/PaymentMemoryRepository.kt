package com.sample.payment.infrastructure

import com.sample.payment.domain.Payment
import com.sample.payment.domain.PaymentRepository

class PaymentMemoryRepository: PaymentRepository {
    private val payments = listOf(
        Payment("P1234", "120", "visa"),
        Payment("P1235", "99", "mc"),
        Payment("P1236", "76", "amex")
    )
    override fun add(payment: Payment) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Payment> = payments

    override fun get(id: String): Payment? {
        return payments.firstOrNull{ id == it.id }
    }
}