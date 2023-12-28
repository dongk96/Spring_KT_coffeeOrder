package com.spartakt.coffee_order.exception

import com.spartakt.coffee_order.web.response.ApiResponseCode

class NotFoundException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.NOT_FOUND
    override var message: String = message
}