package com.sample

import com.sample.domain.Payment
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ApplicationTest {

    @Test
    fun testRoot() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
                assertThat(response.content).isEqualTo("Hello, world!")
            }
        }
    }

    @Test
    fun `should return all stored payments`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/payment").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
                assertThat(response.content).isEqualTo(
                    "[{\"id\":\"P1234\",\"amount\":\"120\",\"paymentMethod\":\"visa\"},{\"id\":\"P1235\",\"amount\":\"99\",\"paymentMethod\":\"mc\"},{\"id\":\"P1236\",\"amount\":\"76\",\"paymentMethod\":\"amex\"}]")
            }
        }

    }

}