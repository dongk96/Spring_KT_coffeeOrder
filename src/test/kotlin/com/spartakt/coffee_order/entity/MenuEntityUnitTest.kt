package com.spartakt.coffee_order.entity

import com.spartakt.coffee_order.domain.entity.Menu
import com.spartakt.coffee_order.domain.entity.Order
import com.spartakt.coffee_order.domain.entity.User
import com.spartakt.coffee_order.domain.entity.role.UserRole
import org.junit.jupiter.api.Test

class MenuEntityUnitTest {
    @Test
    fun 주문_개수_업데이트() {
        val user1 = User("이동규", "1234", UserRole.customer, 10000)
        val user2 = User("홍길동", "5678", UserRole.customer, 10000)
        val user3 = User("이순신", "91011", UserRole.customer, 10000)

        val menu1 = Menu("아메리카노", "갓 볶은 원두", 1000)
        val order1 = Order(user1, menu1)
        val order2 = Order(user2, menu1)
        val order3 = Order(user3, menu1)

    }
}