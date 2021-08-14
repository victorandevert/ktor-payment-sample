package com.sample.payment.domain

interface PaymentRepository {

    fun add (payment: Payment)
    fun get (): List<Payment>
}