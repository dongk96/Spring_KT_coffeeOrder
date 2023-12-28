package com.spartakt.coffee_order.web.response

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class ErrorResponse(
    val code: ApiResponseCode? = null,
    val message: String? = null
)
