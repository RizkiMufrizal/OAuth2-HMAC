package org.rizki.mufrizal.oauth2.hmac.configuration

import org.rizki.mufrizal.oauth2.hmac.helpers.buildUserAuthority
import org.rizki.mufrizal.oauth2.hmac.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 14.34
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.configuration
 * @File WebSecurityConfiguration
 *
 */

@Configuration
@EnableWebSecurity(debug = true)
class WebSecurityConfiguration @Autowired constructor(val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun userDetailsService() = UserDetailsService { username ->
        userRepository.findByUsername(username)
                .map { user ->
                    buildUserForAuthentication(user, buildUserAuthority(roles = user.roles as Set<*>))
                }
                .orElseThrow {
                    UsernameNotFoundException("username not found $username")
                }
    }

    private fun buildUserForAuthentication(user: org.rizki.mufrizal.oauth2.hmac.domain.User, grantedAuthorities: List<GrantedAuthority>): User {
        return User(user.username, user.password, user.isActive as Boolean, true, true, true, grantedAuthorities)
    }

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder?) {
        authenticationManagerBuilder
                ?.userDetailsService(this.userDetailsService())
                ?.passwordEncoder(passwordEncoder())
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}