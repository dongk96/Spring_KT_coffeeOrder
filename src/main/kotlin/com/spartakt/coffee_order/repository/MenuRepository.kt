package com.spartakt.coffee_order.repository

import com.spartakt.coffee_order.domain.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuRepository: JpaRepository<Menu, Long> {
    @Query("select m from Menu m order by m.orderCnt desc limit 3")
    fun findTop3Menu(): List<Menu>
}