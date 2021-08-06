package com.adhityabagasmiwa.phonebook.ui.views

interface BaseView<T> {
    fun onError()
}

interface BasePresenter {
    fun onDestroy()
}