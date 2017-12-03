package org.rizki.mufrizal.oauth2.hmac.helpers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.36
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.helpers
 * @File NotFoundRestHelper
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundRestHelper(id: String, message: String) : RuntimeException("$message $id")