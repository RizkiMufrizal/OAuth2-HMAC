package org.rizki.mufrizal.oauth2.hmac.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import redis.clients.jedis.JedisPoolConfig
import java.time.Duration

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.14
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File RedisSessionConfiguration
 *
 */
@Configuration
@PropertySource("classpath:application.yml")
@EnableRedisHttpSession
class RedisSessionConfiguration @Autowired constructor(val environment: Environment) {

    private fun jedisPoolConfig(): JedisPoolConfig {
        val jedisPoolConfig = JedisPoolConfig()
        jedisPoolConfig.maxTotal = environment.getRequiredProperty("spring.redis.max-total").toInt()
        jedisPoolConfig.maxIdle = environment.getRequiredProperty("spring.redis.max-idle").toInt()
        jedisPoolConfig.minIdle = environment.getRequiredProperty("spring.redis.min-idle").toInt()
        jedisPoolConfig.minEvictableIdleTimeMillis = Duration.ofSeconds(environment.getRequiredProperty("spring.redis.min-evictable-idle-time-millis").toLong()).toMillis()
        jedisPoolConfig.timeBetweenEvictionRunsMillis = Duration.ofSeconds(environment.getRequiredProperty("spring.redis.time-between-eviction-runs-millis").toLong()).toMillis()
        jedisPoolConfig.blockWhenExhausted = environment.getRequiredProperty("spring.redis.block-when-exhausted").toBoolean()
        return jedisPoolConfig
    }

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val jedisConnectionFactory = JedisConnectionFactory(jedisPoolConfig())
        jedisConnectionFactory.port = environment.getRequiredProperty("spring.redis.port").toInt()
        jedisConnectionFactory.hostName = environment.getRequiredProperty("spring.redis.host")
        jedisConnectionFactory.usePool = environment.getRequiredProperty("spring.redis.use-pool").toBoolean()
        return jedisConnectionFactory
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = jedisConnectionFactory()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericToStringSerializer(Any::class.java)
        redisTemplate.valueSerializer = GenericToStringSerializer(Any::class.java)
        return redisTemplate
    }

}