package com.spartakt.coffee_order.domain.entity

import com.spartakt.coffee_order.domain.entity.role.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(username: String, password: String, role: UserRole, point: Long) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long? = null   // 회원 PK

    @Column(name = "username", unique = true)
    var username: String = username   // 회원 이름

    @Column(name = "password")
    var password: String = password   // 회원 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole = role

    @Column(name = "point")
    var point: Long = point

    @OneToMany(mappedBy = "user")
    var orders: MutableList<Order> = mutableListOf()

    fun updatePoint(point: Long) {
        this.point = point
    }

    fun addOrder(order: Order) {
        this.orders.add(order)
    }
}
