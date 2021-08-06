package com.adhityabagasmiwa.data.payload.api.user

data class AuthModel(
    val name: String? = "",
    val email: String? = "",
    val password: String? = "",
    val token: String? = "",
    val id: Int? = -1
)

//data class BaseAuthModel(val status: Boolean, val message: String, val data: AuthModel) : RegisterResponseInterface
data class BaseAuthModel<T>(val status: Boolean, val message: String, val data: T?)
