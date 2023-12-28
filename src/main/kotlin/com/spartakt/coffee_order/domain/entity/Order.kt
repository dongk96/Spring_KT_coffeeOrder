package com.spartakt.coffee_order.domain.entity

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener::class)
class Order(user: User, menu: Menu): BaseTimeEntity() {

    init {
        user.addOrder(this)
        menu.addOrder(this)
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "user_id")
    var user: User = user

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "menu_id")
    var menu: Menu = menu

    @Column(name = "pay_check")
    var payCheck: Boolean = false

    fun updateCheck(check: Boolean) {
        this.payCheck = check
    }
}