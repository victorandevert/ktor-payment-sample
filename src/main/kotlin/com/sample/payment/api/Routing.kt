package com.sample.payment.api

import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.response.*
import io.ktor.routing.*


fun Route.paymentRouting(paymentRepository: PaymentRepository) {
    route("/payments"){
        get {
            when {
                paymentRepository.getAll().isNotEmpty() -> call.respond(paymentRepository.getAll())
                else -> call.respondText("Payments not found", status = NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: ""

            paymentRepository.get(id).fold(
                {
                    call.respondText("Missing payment with id $id", status = NotFound)
                },
                {
                    call.respond(it)
                })
        }
    }
}

fun Application.configureRouting(paymentRepository: PaymentRepository) {
    routing {
        paymentRouting(paymentRepository)
    }

}
