package com.spartakt.coffee_order.api

import com.spartakt.coffee_order.domain.dto.ChargeDto
import com.spartakt.coffee_order.exception.BadRequestException
import com.spartakt.coffee_order.security.JwtAuthenticationToken
import com.spartakt.coffee_order.service.OrderService
import com.spartakt.coffee_order.web.request.ChargePointRequest
import com.spartakt.coffee_order.web.response.SingleResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.net.http.HttpRequest

@RestController
@RequestMapping
class OrderApi(
    private val orderService: OrderService
) {

    @PutMapping("/v3/orders/charge/{userId}")
    @PreAuthorize("hasRole('admin') or hasRole('customer')")
    fun charge(
        @PathVariable userId: Long,
        @Valid @RequestBody request: ChargePointRequest
    ): ResponseEntity<SingleResponse<ChargeDto>> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication is JwtAuthenticationToken) {
            val authUserId = authentication.principal.id
            if(userId != authUserId) {
                throw BadRequestException("사용자가 맞지 않습니다.")
            }
        }

        val user = orderService.charge(userId, request.toServiceDto())

        return ResponseEntity(SingleResponse.successOf(user), HttpStatus.OK)
    }

    @PutMapping("/v3/orders/{coffeeId}")
    @PreAuthorize("hasRole('admin') or hasRole('customer')")
    fun order(
        @PathVariable coffeeId: Long,
    ): ResponseEntity<SingleResponse<String>> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication is JwtAuthenticationToken) {
            val authUserId = authentication.principal.id
            orderService.order(authUserId, coffeeId)
        }

        return ResponseEntity(SingleResponse.success("주문에 성공하셨습니다."), HttpStatus.OK)
    }

    @PutMapping("/v3/orders/calculation/{userId}")
    @PreAuthorize("hasRole('admin') or hasRole('customer')")
    fun pay(
        @PathVariable userId: Long
    ): ResponseEntity<SingleResponse<String>> {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication is JwtAuthenticationToken) {
            val authUserId = authentication.principal.id
            if(userId != authUserId) {
                throw BadRequestException("사용자가 맞지 않습니다.")
            }
            orderService.pay(authUserId)
        }

        return ResponseEntity(SingleResponse.success("결제에 성공하셨습니다."), HttpStatus.OK)
    }
}