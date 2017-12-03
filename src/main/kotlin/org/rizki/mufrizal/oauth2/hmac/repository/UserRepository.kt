package org.rizki.mufrizal.oauth2.hmac.repository

import org.rizki.mufrizal.oauth2.hmac.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.Optional

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.31
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.repository
 * @File UserRepository
 *
 */
interface UserRepository : CrudRepository<User, String> {
    fun findByUsername(username: String): Optional<User>
}