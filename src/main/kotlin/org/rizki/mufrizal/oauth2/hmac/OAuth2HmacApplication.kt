package org.rizki.mufrizal.oauth2.hmac

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OAuth2HmacApplication

fun main(args: Array<String>) {
    SpringApplication.run(OAuth2HmacApplication::class.java, *args)
}