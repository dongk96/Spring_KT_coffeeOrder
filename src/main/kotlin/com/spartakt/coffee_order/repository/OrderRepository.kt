package com.spartakt.coffee_order.repository

import com.spartakt.coffee_order.domain.entity.Order
import com.spartakt.coffee_order.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: JpaRepository<Order, Long> {

    fun findByUser(user: User): List<Order>
}