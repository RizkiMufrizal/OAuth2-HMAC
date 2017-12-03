package org.rizki.mufrizal.oauth2.hmac.configuration

import org.rizki.mufrizal.oauth2.hmac.helpers.buildUserAuthority
import org.rizki.mufrizal.oauth2.hmac.repository.OAuth2ClientDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Service

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.44
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File CassandraClientDetailsService
 *
 */
@Service
class CassandraClientDetailsService : ClientDetailsService {

    @Autowired
    private lateinit var oAuth2ClientDetailRepository: OAuth2ClientDetailRepository

    override fun loadClientByClientId(clientId: String?): ClientDetails? {
        val clientDetail = oAuth2ClientDetailRepository.findByClientId(clientId)
        if (clientDetail.isPresent) {
            val baseClientDetails = BaseClientDetails()
            baseClientDetails.clientId = clientDetail.get().clientId
            baseClientDetails.clientSecret = clientDetail.get().clientSecret
            baseClientDetails.setAuthorizedGrantTypes(clientDetail.get().authorizedGrantTypes)
            baseClientDetails.setScope(clientDetail.get().scope)
            baseClientDetails.authorities = buildUserAuthority(clientDetail.get().authorities as Set<*>)
            return baseClientDetails
        }
        return null
    }
}