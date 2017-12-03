package org.rizki.mufrizal.oauth2.hmac.helpers

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.02
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.helpers
 * @File Authority
 *
 */

fun buildUserAuthority(roles: Set<*>): List<GrantedAuthority> {
    val grantedAuthorities = HashSet<GrantedAuthority>()
    roles.forEach { grantedAuthorities.add(SimpleGrantedAuthority(it as String?)) }
    return ArrayList(grantedAuthorities)
}