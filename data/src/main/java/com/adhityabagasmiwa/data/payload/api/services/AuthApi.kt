package com.adhityabagasmiwa.data.payload.api.services

import com.adhityabagasmiwa.data.payload.api.user.AuthModel
import com.adhityabagasmiwa.data.payload.api.user.BaseAuthModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("v1/signup")
    suspend fun register(@Body body: HashMap<String, Any?>) : BaseAuthModel<AuthModel>

    @POST("v1/signin")
    suspend fun login(@Body body: HashMap<String, Any>) : BaseAuthModel<AuthModel>
}