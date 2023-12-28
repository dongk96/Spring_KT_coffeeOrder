package com.spartakt.coffee_order.exception

import com.spartakt.coffee_order.web.response.ApiResponseCode

class DuplicateException(message: String): BaseException() {
    override var code: ApiResponseCode = ApiResponseCode.DUPLICATE_ENTITY
    override var message: String = message
}