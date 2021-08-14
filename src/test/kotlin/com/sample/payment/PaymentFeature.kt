package com.sample.payment

import com.sample.module
import io.ktor.application.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class PaymentFeature {

    @Test
    fun `should return all stored payments`() {

        withTestApplication(Application::module) {
            handleRequest(Get, "/payments").apply {
                assertThat(response.status()).isEqualTo(OK)
                assertThat(response.content).isEqualToIgnoringWhitespace(getJsonFromFile("payment/payment-methods-response.json"))
            }
        }

    }

    private fun getJsonFromFile(file: String): String{
        val uri = this::class.java.classLoader.getResource(file).toURI()
        return String(Files.readAllBytes(Paths.get(uri)), StandardCharsets.UTF_8)
    }
}

