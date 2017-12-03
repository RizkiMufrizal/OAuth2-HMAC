package org.rizki.mufrizal.oauth2.hmac.helpers

import org.springframework.hateoas.VndErrors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 03 December 2017
 * @Time 15.35
 * @Project OAuth2-HMAC
 * @Package org.rizki.mufrizal.oauth2.hmac.helpers
 * @File ErrorHandlerAdviceHelper
 *
 */

@ControllerAdvice
class ErrorHandlerAdviceHelper {

    @ResponseBody
    @ExceptionHandler(NotFoundRestHelper::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun idNotFoundExceptionHandler(notFoundRestHelper: NotFoundRestHelper): VndErrors {
        return VndErrors("error", notFoundRestHelper.message)
    }

}