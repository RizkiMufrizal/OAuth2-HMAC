package org.rizki.mufrizal.oauth2.hmac.domain

import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.25
 * @Project oauth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.domain
 * @File User
 *
 */

@Table(value = "tb_user_oauth")
data class User(
        @PrimaryKey
        @Column
        val username: String? = null,
        @Column
        val password: String? = null,
        @Column(value = "is_active")
        val isActive: Boolean? = null,
        @Column(value = "roles")
        val roles: Set<String>? = null
)