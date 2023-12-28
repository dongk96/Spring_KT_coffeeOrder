package com.spartakt.coffee_order.web.request

import com.spartakt.coffee_order.domain.dto.ChargeDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class ChargePointRequest(
    @field:NotNull(message = "충전할 포인트는 필수입니다.")
    val point: Long,
) {

    fun toServiceDto(): ChargeDto {
        return ChargeDto(
            point = point
        )
    }
}