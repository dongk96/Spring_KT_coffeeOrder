package com.spartakt.coffee_order.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

data class UserPrincipal(
    val id: Long,
    val username: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(id: Long, username: String, roles: Set<String>) : this(
        id,
        username,
        roles.map { SimpleGrantedAuthority("ROLE_$it" ) })

}