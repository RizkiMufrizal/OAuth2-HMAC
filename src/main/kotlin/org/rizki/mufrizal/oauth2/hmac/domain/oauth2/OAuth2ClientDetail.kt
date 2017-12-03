package org.rizki.mufrizal.oauth2.hmac.domain.oauth2

import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.28
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.domain.oauth2
 * @File OAuth2ClientDetail
 *
 */
@Table(value = "tb_oauth_client_details")
data class OAuth2ClientDetail(
        @PrimaryKey
        @Column(value = "client_id")
        val clientId: String? = null,

        @Column(value = "resource_ids")
        val resourceIds: String? = null,

        @Column(value = "client_secret")
        val clientSecret: String? = null,

        @Column(value = "scope")
        val scope: Set<String>? = null,

        @Column(value = "authorized_grant_types")
        val authorizedGrantTypes: Set<String>? = null,

        @Column(value = "web_server_redirect_uri")
        val webServerRedirectUri: String? = null,

        @Column(value = "authorities")
        val authorities: Set<String>? = null,

        @Column(value = "access_token_validity")
        val accessTokenValidity: Int? = null,

        @Column(value = "refresh_token_validity")
        val refreshTokenValidity: Int? = null,

        @Column(value = "additional_information")
        val additionalInformation: String? = null,

        @Column(value = "autoapprove")
        val autoApprove: Boolean? = null
)