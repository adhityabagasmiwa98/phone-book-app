package com.adhityabagasmiwa.phonebook.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhityabagasmiwa.phonebook.sessions.LocalSession
import com.adhityabagasmiwa.usescases.infrastructures.AuthUseCaseListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AuthViewModel(private val authUseCase: AuthUseCaseListener, private val localSession: LocalSession) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()

    fun register(
        request: HashMap<String, Any?>,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        // using launch
        /*viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)

            flow { emit(authUseCase.register(request)) }
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                    it.message?.let { msg -> onError(msg) }
                }
                .flowOn(Dispatchers.Main)
                .collect { onSuccess(it.token.also { token -> localSession.saveToken(token) }) }

            _isLoading.postValue(false)
        }*/
    }

    fun login(
        request: HashMap<String, Any>,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)

            flow { emit(authUseCase.login(request)) }
                .flowOn(Dispatchers.IO)
                .catch {
                    it.printStackTrace()
                    it.message?.let { msg -> onError(msg) }
                }
                .flowOn(Dispatchers.Main)
                .collect { onSuccess(it.token.also { token -> localSession.saveToken(token) }) }

            _isLoading.postValue(false)
        }
    }
}