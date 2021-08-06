package com.adhityabagasmiwa.phonebook.ui.presenters

import android.widget.Toast
import com.adhityabagasmiwa.phonebook.sessions.LocalSession
import com.adhityabagasmiwa.phonebook.ui.views.MainView
import com.adhityabagasmiwa.usescases.infrastructures.AuthUseCaseListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainPresenter(private val authUseCase: AuthUseCaseListener, private val localSession: LocalSession) : MainView.Presenter, KoinComponent {

    /*private val authUseCase: AuthUseCase = get()*/
    private lateinit var job: Job

    /*fun register(
        request: HashMap<String, Any?>,
        onError: (String) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        job = GlobalScope.launch {
            flow { emit(authUseCase.register(request)) }
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                    it.message?.let { msg -> onError(msg) }
                }
                .flowOn(Dispatchers.Main)
                .collect { onSuccess(it.token.also { token -> localSession.saveToken(token) }) }
        }
    }*/

    override fun onRegister(
        loading: Unit,
        request: HashMap<String, Any?>,
        onError: (String) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        job = GlobalScope.launch {
            flow { emit(authUseCase.register(request)) }
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                    it.message?.let { msg -> onError(msg) }
                }
                .flowOn(Dispatchers.Main)
                .catch {
                    it.message?.let { msg -> onSuccess(msg) }
                }
                .collect { onSuccess(it.token.also { token -> localSession.saveToken(token) }) }
        }
    }

    override fun onLogin(
        loading: Unit,
        request: HashMap<String, Any>,
        onError: (String) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        job = GlobalScope.launch {
            flow { emit(authUseCase.login(request)) }
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                    it.message?.let { msg -> onError(msg) }
                }
                .flowOn(Dispatchers.Main)
                .catch {
                    it.message?.let { msg -> onSuccess(msg) }
                }
                .collect { onSuccess(it.token.also { token -> localSession.saveToken(token) }) }
        }
    }

    fun cancel() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }

    override fun onDestroy() {
    }
}