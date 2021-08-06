package com.adhityabagasmiwa.phonebook.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adhityabagasmiwa.phonebook.databinding.ActivitySignInBinding
import com.adhityabagasmiwa.phonebook.ui.presenters.MainPresenter
import com.adhityabagasmiwa.phonebook.ui.views.MainView
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class SignInActivity : AppCompatActivity(), MainView.ViewLogin {

    private lateinit var binding: ActivitySignInBinding
    private val presenter: MainPresenter by inject()
    //private val viewModel by inject<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            tvRegister.setOnClickListener {
                val mIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(mIntent)
            }

            btnLogin.setOnClickListener {
                onSuccessLogin()
            }
        }

        /*binding.btnLogin.setOnClickListener {
            with(binding) {
                if (edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty()) {
                    viewModel.login(
                        request = hashMapOf(
                            "email" to edtEmail.text.toString(),
                            "password" to edtPassword.text.toString(),
                        ),
                        onError = { error -> it.showMessage(error) },
                        onSuccess = { token ->
                            if (token.isNotEmpty()) {
                                goToHomeActivity()
                            } else {
                                toast("Failed Login")
                            }
                        }
                    )
                } else {
                    toast("Please fill all field !")
                }
            }
        }*/
    }

    override fun onSuccessLogin() {
        with(binding) {
            if (edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty()) {
                presenter.onLogin(
                    loading(),
                    request = hashMapOf(
                        "email" to edtEmail.text.toString(),
                        "password" to edtPassword.text.toString()
                    ),
                    onError = { error -> toast(error) },
                    onSuccess = { token ->
                        if (token.isNotEmpty()) {
                            goToHomeActivity()
                        } else {
                            toast("Failed Login")
                        }
                    }
                )
            } else {
                toast("Please fill all field!")
            }
        }
    }

    override fun onError() {
        toast("Error happen")
    }

    private fun goToHomeActivity() {
        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
    }

    private fun loading() {
        with(binding) {
            pbLoading.visibility = View.VISIBLE
            btnLogin.visibility = View.GONE
        }
    }
}