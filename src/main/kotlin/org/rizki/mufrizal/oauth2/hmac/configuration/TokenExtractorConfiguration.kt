package org.rizki.mufrizal.oauth2.hmac.configuration

import org.rizki.mufrizal.oauth2.hmac.domain.oauth2.OAuth2ClientDetail
import org.rizki.mufrizal.oauth2.hmac.repository.OAuth2ClientDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.security.oauth2.provider.authentication.TokenExtractor
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.util.Base64
import java.util.Optional
import java.util.stream.Collectors
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.servlet.http.HttpServletRequest

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 16.30
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File TokenExtractorConfiguration
 *
 */
@Component
class TokenExtractorConfiguration : TokenExtractor {

    @Autowired
    private lateinit var tokenStore: TokenStore

    @Autowired
    private lateinit var oAuth2ClientDetailRepository: OAuth2ClientDetailRepository

    override fun extract(request: HttpServletRequest): Authentication? {
        val accessToken = extractHeaderToken(httpServletRequest = request)
        return when {
            accessToken.isNullOrEmpty() -> null
            else -> {
                val preAuthenticatedAuthenticationToken = PreAuthenticatedAuthenticationToken(accessToken, "")
                preAuthenticatedAuthenticationToken
            }
        }
    }

    protected fun extractHeaderToken(httpServletRequest: HttpServletRequest): String? {
        val headerSignature = httpServletRequest.getHeader("HMAC-Signature")
        val headerTimestamp = httpServletRequest.getHeader("HMAC-Timestamp")
        val headerAuthorization = httpServletRequest.getHeader("Authorization")
        when {
            headerAuthorization.isNullOrEmpty() -> return null
            headerSignature.isNullOrBlank() -> return null
            headerTimestamp.isNullOrEmpty() -> return null
            else -> {
                if (headerAuthorization.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase())) {
                    val accessToken = headerAuthorization.substring(OAuth2AccessToken.BEARER_TYPE.length).trim()
                    val clientId = tokenStore.readAuthentication(accessToken).oAuth2Request.clientId

                    return when {
                        accessToken.isEmpty() -> null
                        else -> {
                            httpServletRequest.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, accessToken)
                            return when {
                                validateHMAC(httpServletRequest = httpServletRequest, hmacSignature = headerSignature, hmacTimestamp = headerTimestamp, oauth2AccessToken = accessToken, clientId = clientId) == false -> return null
                                else ->
                                    accessToken
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    protected fun validateHMAC(httpServletRequest: HttpServletRequest, hmacSignature: String, hmacTimestamp: String, oauth2AccessToken: String, clientId: String): Boolean? {
        val oAuth2ClientDetail = oAuth2ClientDetailRepository.findByClientId(clientId = clientId)
        return when {
            !oAuth2ClientDetail.isPresent -> null
            else ->
                if ("POST".equals(httpServletRequest.method, ignoreCase = true) || "PUT".equals(httpServletRequest.method, ignoreCase = true)) {
                    val body = httpServletRequest.reader.lines().collect(Collectors.joining(System.lineSeparator()))
                            .replace("[\n\r\t]", "")
                            .replace("\\s", "")
                    val hash = "$oauth2AccessToken:$hmacTimestamp:$body"

                    generateHMAC(oAuth2ClientDetail = oAuth2ClientDetail, hash = hash) == hmacSignature
                } else {
                    val hash = "$oauth2AccessToken:$hmacTimestamp"
                    generateHMAC(oAuth2ClientDetail = oAuth2ClientDetail, hash = hash) == hmacSignature
                }
        }
    }

    protected fun generateHMAC(oAuth2ClientDetail: Optional<OAuth2ClientDetail>, hash: String): String {
        val secretKey = SecretKeySpec(oAuth2ClientDetail.get().clientSecret?.toByteArray(), "HmacSHA512")
        val hmac = Mac.getInstance("HmacSHA512")
        hmac.init(secretKey)

        val encoder = Base64.getEncoder()
        return encoder.encodeToString(hmac.doFinal(hash.toByteArray(charset = Charset.defaultCharset())))
    }
}