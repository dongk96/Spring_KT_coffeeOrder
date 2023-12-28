package com.spartakt.coffee_order.service

import com.spartakt.coffee_order.domain.entity.User
import com.spartakt.coffee_order.domain.entity.role.UserRole
import com.spartakt.coffee_order.exception.BadRequestException
import com.spartakt.coffee_order.exception.DuplicateException
import com.spartakt.coffee_order.exception.NotFoundException
import com.spartakt.coffee_order.repository.UserRepository
import com.spartakt.coffee_order.security.JwtPlugin
import lombok.RequiredArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) {

    @Transactional
    fun register(username: String, password: String, role: UserRole) {
        val result = userRepository.existsByUsername(username)
        if (result) {
            throw DuplicateException("중복된 username 입니다.")
        }
        val user = User(username, passwordEncoder.encode(password), role, 0)
        userRepository.save(user)
    }

    fun login(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
            .orElseThrow { NotFoundException("회원을 찾을 수 없습니다.") }

        if(!passwordEncoder.matches(password, user.password)) {
            throw BadRequestException("비밀번호가 틀렸습니다.")
        }

        return jwtPlugin.generateAccessToken(user.userId.toString(), user.username, user.role)
    }
}