package com.adhityabagasmiwa.usescases.cases

import com.adhityabagasmiwa.data.persistences.repositories.AuthRepositoryInterface
import com.adhityabagasmiwa.domain.models.AuthDomainModel
import com.adhityabagasmiwa.usescases.infrastructures.AuthUseCaseListener

// kotlin flow
/*
class AuthUseCase(private var repository: AuthRepository, private var context: CoroutineContext) {
    private lateinit var job: Job

    fun register(listener: UseCaseListener<AuthDomainModel>, auth: HashMap<String, Any?>) {
        job = GlobalScope.launch(context) {
            repository.register(auth)
                .flowOn(Dispatchers.IO)
                .catch {
                    withContext(Dispatchers.Main) {
                        listener.onError(it)
                    }
                }
                .collect {
                    withContext(Dispatchers.Main) {
                        listener.onComplete(it)
                    }
                }
        }
    }

    fun cancel() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }
}*/

class AuthUseCaseImpl(private val authRepository: AuthRepositoryInterface) : AuthUseCaseListener {
    override suspend fun register(request: HashMap<String, Any?>): AuthDomainModel {
        return authRepository.register(request)
    }

    override suspend fun login(request: HashMap<String, Any>): AuthDomainModel {
        return authRepository.login(request)
    }
    /*override suspend fun register(request: HashMap<String, Any?>) = authRepository.register(request)
    override suspend fun login(request: HashMap<String, Any>) = authRepository.login(request)*/
}