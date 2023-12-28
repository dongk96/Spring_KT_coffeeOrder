package com.spartakt.coffee_order.api

import com.spartakt.coffee_order.domain.dto.MenuDto
import com.spartakt.coffee_order.exception.BadRequestException
import com.spartakt.coffee_order.service.MenuService
import com.spartakt.coffee_order.web.request.OrderCreateRequest
import com.spartakt.coffee_order.web.request.OrderUpdateRequest
import com.spartakt.coffee_order.web.response.ListResponse
import com.spartakt.coffee_order.web.response.SingleResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class MenuApi(private val menuService: MenuService) {
    @GetMapping("/v1/menus")
    fun findAll(): ResponseEntity<ListResponse<MenuDto>> {
        val menus = menuService.findAll()
        return ResponseEntity(ListResponse.successOf(menus), HttpStatus.OK)
    }

    @GetMapping("/v1/menus/{coffeeId}")
    fun find(
        @PathVariable coffeeId: Long
    ): ResponseEntity<SingleResponse<MenuDto>> {
        val menu = menuService.findById(coffeeId)
        return ResponseEntity(SingleResponse.successOf(menu), HttpStatus.OK)
    }

    @PostMapping("/v2/menus")
    @PreAuthorize("hasRole('admin')")
    fun create(
        @Valid @RequestBody request: OrderCreateRequest
    ): ResponseEntity<SingleResponse<MenuDto>> {
        val menu = menuService.create(request.toServiceDto())
        return ResponseEntity(SingleResponse.successOf(menu), HttpStatus.OK)
    }

    @PutMapping("/v2/menus/{coffeeId}")
    @PreAuthorize("hasRole('admin')")
    fun update(
        @PathVariable coffeeId: Long,
        @Valid @RequestBody request: OrderUpdateRequest
    ): ResponseEntity<SingleResponse<MenuDto>> {
        if(coffeeId != request.coffeeId)
            throw BadRequestException("수정하려는 커피 메뉴가 아닙니다.")

        val menu = menuService.update(request.toServiceDto())

        return ResponseEntity(SingleResponse.successOf(menu), HttpStatus.OK)
    }

    @DeleteMapping("/v2/menus/{coffeeId}")
    @PreAuthorize("hasRole('admin')")
    fun delete(
        @PathVariable coffeeId: Long
    ): ResponseEntity<SingleResponse<String>> {
        menuService.delete(coffeeId)
        return ResponseEntity(SingleResponse.success(), HttpStatus.OK)
    }
}