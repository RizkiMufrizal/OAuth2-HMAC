package org.rizki.mufrizal.oauth2.hmac.helpers

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.ResourceSupport

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.35
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.helpers
 * @File HateoasResource
 *
 */

class HateoasResource @JsonCreator
constructor(@param:JsonProperty("content") val content: Any? = null) : ResourceSupport()