package com.spartakt.coffee_order.repository

import com.spartakt.coffee_order.domain.entity.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository: JpaRepository<Menu, Long> {
}