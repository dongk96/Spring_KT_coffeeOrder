package com.spartakt.coffee_order.service

import com.spartakt.coffee_order.domain.dto.ChargeDto
import com.spartakt.coffee_order.domain.entity.Order
import com.spartakt.coffee_order.exception.BadRequestException
import com.spartakt.coffee_order.exception.NotFoundException
import com.spartakt.coffee_order.repository.MenuRepository
import com.spartakt.coffee_order.repository.OrderRepository
import com.spartakt.coffee_order.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class OrderService(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository,
    private val orderRepository: OrderRepository
) {

    @Transactional
    fun charge(userId: Long, dto: ChargeDto): ChargeDto {
        val user = userRepository.findById(userId)
            .orElseThrow { NotFoundException("사용자를 찾을 수 없습니다.") }
        user.updatePoint(dto.point)

        return ChargeDto.fromEntity(user)
    }

    @Transactional
    fun order(userId: Long, coffeeId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { NotFoundException("사용자를 찾을 수 없습니다.") }
        val menu = menuRepository.findById(coffeeId)
            .orElseThrow { NotFoundException("메뉴를 찾을 수 없습니다.")}

        val order = Order(user, menu)
        orderRepository.save(order)
    }

    @Transactional
    fun pay(userId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow { NotFoundException("사용자를 찾을 수 없습니다.") }

        val orderMenus = user.orders
        var orderPrice = 0

        for(i in orderMenus) {
            orderPrice = (orderPrice + i.menu.price).toInt()
        }

        if(user.point < orderPrice) {
            throw BadRequestException("포인트가 부족합니다.")
        }

        user.updatePoint(user.point - orderPrice)

        val userOrders = orderRepository.findByUser(user)

        for( i in userOrders ) {
            i.updateCheck(true)
            i.menu.updateOrderCnt(i.menu.orderCnt + 1)
        }
    }
}