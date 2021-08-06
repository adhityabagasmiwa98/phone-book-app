package com.adhityabagasmiwa.phonebook.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.adhityabagasmiwa.phonebook.databinding.ActivitySignUpBinding
import com.adhityabagasmiwa.phonebook.ui.presenters.MainPresenter
import com.adhityabagasmiwa.phonebook.ui.views.MainView
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class SignUpActivity : AppCompatActivity(), MainView.ViewRegister {

    //    private val presenter = MainPresenter(this)
    private val presenter: MainPresenter by inject()
    private lateinit var binding: ActivitySignUpBinding
//    private val viewModel by inject<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.btnRegister.setOnClickListener {
            with(binding) {
                if (edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty() && edtName.text.isNotEmpty()) {
                    viewModel.register(
                        request = hashMapOf(
                            "email" to edtEmail.text.toString(),
                            "password" to edtPassword.text.toString(),
                            "name" to edtName.text.toString()
                        ),
                        onError = { error -> it.showError(error) },
                        onSuccess =  { token ->
                            if (token.isNotEmpty()) {
                                toast("Success Register")
                                goToSignInActivity()
                            } else {
                                toast("Failed Register")
                            }
                        }
                    )
                } else {
                    toast("Please filled all !")
                }
            }
        }*/
        /* binding.btnRegister.setOnClickListener {
             with(binding) {
                 if (edtEmail.text.isNotEmpty() && edtName.text.isNotEmpty() && edtPassword.text.isNotEmpty()) {
                     presenter.register(
                         request = hashMapOf(
                             "email" to edtEmail.text.toString(),
                             "name" to edtName.text.toString(),
                             "password" to edtPassword.text.toString()
                         ),
                         onError = { error -> it.showMessage(error)
                         },
                         onSuccess = { message ->
                             if (message == "Successfull") {
                                 it.showMessage("Success register")
                                 goToSignInActivity()
                             } else {
                                 it.showMessage("Failed register")
                             }
                         }
                     )
                 } else {
                     toast("Please fill all field")
                 }
             }
         }*/

        binding.btnRegister.setOnClickListener {
            onSuccessRegister()
        }

        binding.tvLogin.setOnClickListener {
            goToSignInActivity()
        }
    }

    override fun onSuccessRegister() {
        with(binding) {
            if (edtName.text.isNotEmpty() && edtEmail.text.isNotEmpty() && edtPassword.text.isNotEmpty()) {
                presenter.onRegister(
                    loading(true),
                    request = hashMapOf(
                        "name" to edtName.text.toString(),
                        "email" to edtEmail.text.toString(),
                        "password" to edtPassword.text.toString()
                    ),
                    onError = { error -> toast(error) },
                    onSuccess = { token ->
                        if (token.isNotEmpty()) {
                            loading(false)
                            goToSignInActivity()
                        } else {
                            toast("Failed Register")
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

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun goToSignInActivity() {
        val mIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
        startActivity(mIntent)
    }

    private fun loading(state: Boolean) {
        with(binding) {
            if (state) {
                pbLoading.visibility = View.VISIBLE
                btnRegister.visibility = View.GONE
            } else {
                pbLoading.visibility = View.GONE
            }
        }
    }
}
