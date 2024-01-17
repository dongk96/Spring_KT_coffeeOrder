package com.spartakt.coffee_order.entity

import com.spartakt.coffee_order.domain.entity.Menu
import com.spartakt.coffee_order.domain.entity.Order
import com.spartakt.coffee_order.domain.entity.User
import com.spartakt.coffee_order.domain.entity.role.UserRole
import org.junit.jupiter.api.Test

class UserEntityUnitTest {
    @Test
    fun 고객_포인트_차감() {
        val user1 = User("이동규", "1234", UserRole.customer, 10000)
        val menu1 = Menu("아메리카노", "갓 볶은 원두", 1000)
        val order1 = Order(user1, menu1)
    }
}