package com.spartakt.coffee_order.web.request

import com.spartakt.coffee_order.domain.dto.MenuDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class OrderCreateRequest (
    @field:NotBlank(message = "커피 이름은 필수 입니다.")
    private val coffeeName: String,

    @field:NotBlank(message = "커피 설명은 필수 입니다.")
    private val description: String,

    @field:NotNull(message = "커피 가격은 필수 입니다.")
    private val price: Long
) {
    fun toServiceDto(): MenuDto {
        return MenuDto(
            coffeeName = coffeeName,
            description = description,
            price = price
        )
    }
}
