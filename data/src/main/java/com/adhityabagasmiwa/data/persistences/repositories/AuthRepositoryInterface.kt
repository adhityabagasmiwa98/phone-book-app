package com.adhityabagasmiwa.data.persistences.repositories

//import com.adhityabagasmiwa.data.persistences.contracts.AuthPersistenceInterface
import com.adhityabagasmiwa.domain.models.AuthDomainModel

// kotlin flow
/*
class AuthRepository(private val persistence: AuthPersistenceInterface, private val authDataMapperImpl: AuthDataMapperImpl): RepositoryInterface {
    suspend fun register(auth: HashMap<String, Any?>): Flow<AuthDomainModel> {
        return persistence.register(auth).map {
            authDataMapperImpl.toDomain(it)
        }
    }
}*/

interface AuthRepositoryInterface {
    suspend fun register(auth: HashMap<String, Any?>): AuthDomainModel
    suspend fun login(auth: HashMap<String, Any>): AuthDomainModel
}
