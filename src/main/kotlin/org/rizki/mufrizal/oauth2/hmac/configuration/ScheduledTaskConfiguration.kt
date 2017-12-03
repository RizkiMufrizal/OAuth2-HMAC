package org.rizki.mufrizal.oauth2.hmac.configuration

import com.datastax.driver.core.utils.UUIDs
import org.rizki.mufrizal.oauth2.hmac.domain.Barang
import org.rizki.mufrizal.oauth2.hmac.domain.JenisBarang
import org.rizki.mufrizal.oauth2.hmac.domain.User
import org.rizki.mufrizal.oauth2.hmac.domain.oauth2.OAuth2ClientDetail
import org.rizki.mufrizal.oauth2.hmac.repository.BarangRepository
import org.rizki.mufrizal.oauth2.hmac.repository.OAuth2ClientDetailRepository
import org.rizki.mufrizal.oauth2.hmac.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.29
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File ScheduledTaskConfiguration
 *
 */
@Configuration
@EnableScheduling
class ScheduledTaskConfiguration {

    @Autowired
    private lateinit var userRepositoty: UserRepository

    @Autowired
    private lateinit var oAuth2ClientDetailRepository: OAuth2ClientDetailRepository

    @Autowired
    private lateinit var barangRepository: BarangRepository

    @Scheduled(fixedRate = 3600000)
    fun insertData() {
        if (!this.userRepositoty.findByUsername("rizki").isPresent) {
            this.userRepositoty.save(User(
                    username = "rizki",
                    password = BCryptPasswordEncoder().encode("mufrizal"),
                    isActive = true,
                    roles = setOf("ROLE_ADMIN", "ROLE_USER")
            ))
        }

        if (!this.oAuth2ClientDetailRepository.findByClientId("clientid").isPresent) {
            this.oAuth2ClientDetailRepository.save(OAuth2ClientDetail(
                    clientId = "clientid",
                    resourceIds = "RESOURCE_ID_BARANG",
                    clientSecret = "secret",
                    scope = setOf("read", "write"),
                    authorizedGrantTypes = setOf("password", "client_credentials", "refresh_token", "authorization_code", "implicit"),
                    webServerRedirectUri = " ",
                    authorities = setOf("ADMIN", "CLIENT", "ADMINISTRATOR"),
                    accessTokenValidity = 3600,
                    refreshTokenValidity = 3600,
                    additionalInformation = "{\"additional_param\":\"hello OAuth2\"}",
                    autoApprove = true
            ))
        }

        //just for development
        if (this.barangRepository.findAll().count() == 0) {
            for (b in 0..10) {
                this.barangRepository.save(Barang(
                        idBarang = UUIDs.timeBased(),
                        namaBarang = "Nama $b",
                        jenisBarang = if (b % 2 == 0) JenisBarang.cair else JenisBarang.gas
                ))
            }
        }
    }
}