package com.sample.payment.api

import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.response.*
import io.ktor.routing.*


fun Route.paymentRouting(paymentRepository: PaymentRepository) {
    route("/payments"){
        get {
            if (paymentRepository.get().isNotEmpty()){
                call.respond(paymentRepository.get())
            }else{
                call.respondText("Payments not found", status = NotFound)
            }
        }
    }
}

fun Application.configureRouting(paymentRepository: PaymentRepository) {
    routing {
        paymentRouting(paymentRepository)
    }

}
