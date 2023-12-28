package com.spartakt.coffee_order.service

import com.spartakt.coffee_order.domain.dto.MenuDto
import com.spartakt.coffee_order.domain.entity.Menu
import com.spartakt.coffee_order.exception.NotFoundException
import com.spartakt.coffee_order.repository.MenuRepository
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class MenuService(private val menuRepository: MenuRepository) {

    fun findAll(): List<MenuDto> {
        val menus = menuRepository.findAll()
        return MenuDto.fromEntities(menus)
    }

    fun findById(coffeeId: Long): MenuDto {
        val menu = menuRepository.findById(coffeeId)
            .orElseThrow { NotFoundException("해당 메뉴를 찾을 수 없습니다.")}
        return MenuDto.fromEntity(menu)
    }

    @Transactional
    fun create(dto: MenuDto): MenuDto {
        val menu = Menu(dto.coffeeName, dto.description, dto.price)
        val saveMenu = menuRepository.save(menu)

        return MenuDto.fromEntity(saveMenu)
    }

    @Transactional
    fun update(dto: MenuDto): MenuDto {
        val menu = menuRepository.findById(dto.coffeeId!!)
            .orElseThrow { NotFoundException("수정할 메뉴를 찾을 수 없습니다.") }
        menu.update(dto.coffeeName, dto.description, dto.price)
        return MenuDto.fromEntity(menu)
    }

    fun delete(coffeeId: Long) {
        val menu = menuRepository.findById(coffeeId)
            .orElseThrow { NotFoundException("삭제할 메뉴를 찾을 수 없습니다.") }
        menuRepository.delete(menu)
    }
}