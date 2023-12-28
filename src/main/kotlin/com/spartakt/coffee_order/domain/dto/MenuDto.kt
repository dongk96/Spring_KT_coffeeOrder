package com.spartakt.coffee_order.domain.dto

import com.spartakt.coffee_order.domain.entity.Menu

data class MenuDto(
    var coffeeId : Long? = null,
    var coffeeName : String,
    var description : String,
    var price : Long
) {
    companion object {
        fun fromEntity(menu: Menu): MenuDto {

            return MenuDto(
                coffeeId = menu.coffeeId,
                coffeeName = menu.coffeeName,
                description = menu.description,
                price = menu.price
            )
        }

        fun fromEntities(menus: List<Menu>): List<MenuDto> {
            return menus.map {
                val dto = MenuDto(
                    coffeeId = it.coffeeId,
                    coffeeName = it.coffeeName,
                    description = it.description,
                    price = it.price
                )

                dto
            }
        }
    }
}
