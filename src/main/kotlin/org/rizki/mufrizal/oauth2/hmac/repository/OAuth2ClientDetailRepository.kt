package org.rizki.mufrizal.oauth2.hmac.repository

import org.rizki.mufrizal.oauth2.hmac.domain.oauth2.OAuth2ClientDetail
import org.springframework.data.repository.CrudRepository
import java.util.Optional

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.46
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.repository
 * @File OAuth2ClientDetailRepository
 *
 */
interface OAuth2ClientDetailRepository : CrudRepository<OAuth2ClientDetail, String> {
    fun findByClientId(clientId: String? = null): Optional<OAuth2ClientDetail>
}