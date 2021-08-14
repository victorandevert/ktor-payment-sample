package com.sample.payment

import com.sample.configuration.setup
import com.sample.module
import com.sample.payment.domain.PaymentRepository
import io.ktor.application.*
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.junit5.AutoCloseKoinTest
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class PaymentFeature: AutoCloseKoinTest() {

    private val mockSetup = module {
        single<PaymentRepository> {
            mock {on { getAll() } doReturn emptyList()}
        }
    }

    @Test
    fun `should return all stored payments`() {
        startKoin { modules(setup) }
        withTestApplication(Application::module) {
            handleRequest(Get, "/payments").apply {
                assertThat(response.status()).isEqualTo(OK)
                assertThat(response.content).isEqualToIgnoringWhitespace(getJsonFromFile("payment/all-payments-response.json"))
            }
        }
    }

    @Test
    fun `should return a specific payment`() {
        startKoin { modules(setup) }
        withTestApplication(Application::module) {
            handleRequest(Get, "/payments/P1235").apply {
                assertThat(response.status()).isEqualTo(OK)
                assertThat(response.content).isEqualToIgnoringWhitespace(getJsonFromFile("payment/single-payment-response.json"))
            }
        }
    }

    @Test
    fun `should return an error when payments cannot be found`() {
        startKoin { modules(mockSetup) }
        withTestApplication(Application::module) {
            handleRequest(Get, "/payments").apply {
                assertThat(response.status()).isEqualTo(NotFound)
                assertThat(response.content).isEqualTo("Payments not found")
            }
        }
    }

    @Test
    fun `should return an error when a payment cannot be found`() {
        startKoin { modules(setup) }
        withTestApplication(Application::module) {
            handleRequest(Get, "/payments/P0000").apply {
                assertThat(response.status()).isEqualTo(NotFound)
                assertThat(response.content).isEqualTo("Missing payment with id P0000")
            }
        }
    }

    @Test
    fun `should return an error when a payment id is missing`() {
        startKoin { modules(setup) }
        withTestApplication(Application::module) {
            handleRequest(Get, "/payments/").apply {
                assertThat(response.status()).isEqualTo(BadRequest)
                assertThat(response.content).isEqualTo("Missing id")
            }
        }
    }
    private fun getJsonFromFile(file: String): String{
        val uri = this::class.java.classLoader.getResource(file).toURI()
        return String(Files.readAllBytes(Paths.get(uri)), StandardCharsets.UTF_8)
    }
}

