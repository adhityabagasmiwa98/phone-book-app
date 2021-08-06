package com.adhityabagasmiwa.data.persistences.repositories

import com.adhityabagasmiwa.data.payload.api.services.AuthApi
import com.adhityabagasmiwa.data.payload.api.user.AuthModel
import com.adhityabagasmiwa.data.payload.contracts.AuthResponseInterface
import com.adhityabagasmiwa.data.payload.contracts.RepositoryInterface
import com.adhityabagasmiwa.data.persistences.contracts.AuthPersistenceInterface
import com.adhityabagasmiwa.data.persistences.mappers.AuthDataMapper
import com.adhityabagasmiwa.domain.models.AuthDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(private val authApi: AuthApi, private val authDataMapper: AuthDataMapper) : AuthRepositoryInterface {
    override suspend fun register(auth: HashMap<String, Any?>): AuthDomainModel {
        return authDataMapper.toDomain(authApi.register(auth).data ?: AuthModel())
    }

    override suspend fun login(auth: HashMap<String, Any>): AuthDomainModel {
        return authDataMapper.toDomain(authApi.login(auth).data ?: AuthModel())
    }
    /* override suspend fun register(auth: HashMap<String, Any?>) =
         authDataMapper.toDomain(authApi.register(auth).data ?: AuthModel())

     override suspend fun login(auth: HashMap<String, Any>) =
         authDataMapper.toDomain(authApi.login(auth).data ?: AuthModel())*/
}