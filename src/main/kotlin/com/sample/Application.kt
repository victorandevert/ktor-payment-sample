package com.sample

import com.sample.payment.api.configureRouting
import com.sample.payment.domain.PaymentRepository
import com.sample.payment.infrastructure.PaymentMemoryPaymentRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit =
    EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    install(Koin){
        modules(beanConfiguration)
    }
    val paymentRepository: PaymentRepository by inject()
    configureRouting(paymentRepository)
}

val beanConfiguration = module {
    single<PaymentRepository> { PaymentMemoryPaymentRepository() }
}