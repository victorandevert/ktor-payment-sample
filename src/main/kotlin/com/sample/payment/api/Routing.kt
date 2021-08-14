package com.sample.payment.api

import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.paymentRouting(paymentRepository: PaymentRepository) {
    route("/payments"){
        get {
            call.respond(paymentRepository.get())
        }
    }
}

fun Application.configureRouting(paymentRepository: PaymentRepository) {
    routing {
        paymentRouting(paymentRepository)
    }

}
