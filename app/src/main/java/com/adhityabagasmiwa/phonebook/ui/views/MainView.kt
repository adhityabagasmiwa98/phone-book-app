package com.adhityabagasmiwa.phonebook.ui.views

interface MainView {
    interface ViewRegister : BaseView<Presenter> {
        fun onSuccessRegister()
    }

    interface ViewLogin : BaseView<Presenter> {
        fun onSuccessLogin()
    }

    interface Presenter : BasePresenter {
        fun onRegister(loading: Unit, request: HashMap<String, Any?>, onError: (String) -> Unit, onSuccess: (String) -> Unit)
        fun onLogin(loading: Unit, request: HashMap<String, Any>, onError: (String) -> Unit, onSuccess: (String) -> Unit)
    }
}