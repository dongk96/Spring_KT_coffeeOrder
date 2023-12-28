package com.spartakt.coffee_order.web.request

import com.spartakt.coffee_order.domain.dto.MenuDto
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class OrderUpdateRequest (
    @field:NotNull(message = "커피 ID는 필수 입니다.")
    val coffeeId: Long?,

    @field:NotBlank(message = "커피 이름은 필수 입니다.")
    val coffeeName: String,

    @field:NotBlank(message = "커피 설명은 필수 입니다.")
    val description: String,

    @field:NotBlank(message = "커피 가격은 필수 입니다.")
    val price: Long
) {
    fun toServiceDto(): MenuDto {
        return MenuDto(
            coffeeId = coffeeId,
            coffeeName = coffeeName,
            description = description,
            price = price
        )
    }
}