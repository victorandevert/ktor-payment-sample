package com.sample.configuration

import com.sample.payment.domain.PaymentRepository
import com.sample.payment.infrastructure.PaymentMemoryPaymentRepository
import org.koin.dsl.module

val setup = module {
    single<PaymentRepository> { PaymentMemoryPaymentRepository() }
}