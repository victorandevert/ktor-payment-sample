package com.sample.api

import com.sample.domain.Payment
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*


fun Route.paymentRouting(){
    route("/payment"){
        get {
            call.respond(listOf(
                    Payment("P1234", "120", "visa"),
            Payment("P1235", "99", "mc"),
            Payment("P1236", "76", "amex")
            ))
        }
    }
}

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        paymentRouting()
    }

}
