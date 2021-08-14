package com.sample

import com.sample.configuration.setup
import com.sample.payment.api.configureRouting
import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.ktor.ext.inject

fun main(args: Array<String>) {
    startKoin { modules(setup) }
    EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    val paymentRepository: PaymentRepository by inject()
    configureRouting(paymentRepository)
}