package org.rizki.mufrizal.oauth2.hmac.service.impl

import org.rizki.mufrizal.oauth2.hmac.service.OAuth2AccessTokenRedis
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 17.52
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.service.impl
 * @File OAuth2AccessTokenRedisImpl
 *
 */
@Service
class OAuth2AccessTokenRedisImpl constructor(val redisTemplate: RedisTemplate<String, Any>) : OAuth2AccessTokenRedis {
    override fun getKeyRedis(key: String): Set<ByteArray> {
        return redisTemplate.connectionFactory.connection.keys(key.toByteArray())
    }
}