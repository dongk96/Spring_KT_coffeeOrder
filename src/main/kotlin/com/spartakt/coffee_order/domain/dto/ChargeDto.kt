package com.spartakt.coffee_order.domain.dto

import com.spartakt.coffee_order.domain.entity.User

class ChargeDto(
    var point: Long
) {
    companion object {
        fun fromEntity(user: User): ChargeDto {

            return ChargeDto(
                point = user.point
            )
        }
    }
}