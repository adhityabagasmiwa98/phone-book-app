package com.adhityabagasmiwa.data.persistences.mappers

import com.adhityabagasmiwa.data.payload.api.user.AuthModel
import com.adhityabagasmiwa.domain.models.AuthDomainModel

interface AuthDataMapper {
    fun toDomain(model: AuthModel): AuthDomainModel
}

class AuthDataMapperImpl : AuthDataMapper {
    override fun toDomain(model: AuthModel): AuthDomainModel {
        return AuthDomainModel(
            name = model.name ?: "",
            email = model.email ?: "",
            password = model.password ?: "",
            token = model.token ?: "",
            id = model.id ?: -1
        )
    }
    /*override fun toDomain(model: AuthModel) = AuthDomainModel(
        name = model.name ?: "",
        email = model.email ?: "",
        password = model.password ?: "",
        token = model.token ?: "",
        id = model.id ?: -1
    )*/
}