package com.sample.payment.api

import com.sample.payment.domain.Payment
import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.request.*
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
            val id = call.parameters["id"].orEmpty()

            paymentRepository.get(id).fold(
                {call.respondText("Missing payment with id $id", status = NotFound)},
                {call.respond(it)})
        }
    }
}

fun Route.authorizationRouting(paymentRepository: PaymentRepository){
    route("/payments"){
        post("/{id}/authorization") {
            val id = call.parameters["id"].orEmpty()
            val payment = call.receive<Payment>()
            when (id) {
                payment.id -> call.respond(status = Created, paymentRepository.add(id, payment))
                else -> call.respondText("Payment ID does not match", status = BadRequest)
            }

        }
    }
}

fun Application.configureRouting(paymentRepository: PaymentRepository) {
    routing {
        paymentRouting(paymentRepository)
        authorizationRouting(paymentRepository)
    }

}
