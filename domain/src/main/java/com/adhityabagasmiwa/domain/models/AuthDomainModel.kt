package com.adhityabagasmiwa.domain.models

data class AuthDomainModel(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val token: String = "",
    val id: Int = -1
)
