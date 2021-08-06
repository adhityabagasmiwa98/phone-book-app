package com.adhityabagasmiwa.usescases.infrastructures

// not use
interface UseCaseListener<T> {
    fun onError(e: Throwable)
    fun onComplete(data: T)
}