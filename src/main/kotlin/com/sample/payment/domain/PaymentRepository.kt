package com.sample.payment.domain

interface PaymentRepository {

    fun add (payment: Payment)
    fun getAll (): List<Payment>
    fun get(id: String): Payment?
}