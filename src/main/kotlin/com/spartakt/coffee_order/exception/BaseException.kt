package com.spartakt.coffee_order.exception

import com.spartakt.coffee_order.web.response.ApiResponseCode

abstract class BaseException: RuntimeException() {
    open lateinit var code: ApiResponseCode
    override lateinit var message: String
}