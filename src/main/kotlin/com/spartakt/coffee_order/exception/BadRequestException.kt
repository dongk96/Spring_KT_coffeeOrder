package com.spartakt.coffee_order.exception

import com.spartakt.coffee_order.web.response.ApiResponseCode

class BadRequestException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.BAD_REQUEST
    override var message: String = message
}