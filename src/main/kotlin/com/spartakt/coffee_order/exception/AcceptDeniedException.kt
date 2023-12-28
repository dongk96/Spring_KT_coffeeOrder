package com.spartakt.coffee_order.exception

import com.spartakt.coffee_order.web.response.ApiResponseCode

class AcceptDeniedException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.NOT_ACCEPTABLE
    override var message: String = message
}