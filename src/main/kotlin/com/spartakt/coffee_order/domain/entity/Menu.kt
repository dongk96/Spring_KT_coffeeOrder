package com.spartakt.coffee_order.domain.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@EntityListeners(AuditingEntityListener::class)
class Menu(coffeeName: String, description: String, price: Long) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val coffeeId: Long? = null

    @Column(name = "coffeeName")
    var coffeeName: String = coffeeName

    @Column(name = "description")
    var description: String = description

    @Column(name = "price")
    var price: Long = price

    @OneToMany(mappedBy = "menu")
    var orders: MutableList<Order> = mutableListOf()

    fun update(coffeeName: String, description: String, price: Long) {
        this.coffeeName = coffeeName
        this.description = description
        this.price = price
    }

    fun addOrder(order: Order) {
        this.orders.add(order)
    }
}