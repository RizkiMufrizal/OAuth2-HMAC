package org.rizki.mufrizal.oauth2.hmac.service


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 17.52
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.service
 * @File OAuth2AccessTokenRedis
 *
 */
interface OAuth2AccessTokenRedis {
    fun getKeyRedis(key: String): Set<ByteArray>
}