package com.adhityabagasmiwa.usescases.infrastructures

import com.adhityabagasmiwa.domain.models.AuthDomainModel

interface AuthUseCaseListener {
    suspend fun register(request: HashMap<String, Any?>): AuthDomainModel
    suspend fun login(request: HashMap<String, Any>): AuthDomainModel
}