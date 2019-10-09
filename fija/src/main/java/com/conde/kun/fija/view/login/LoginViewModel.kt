package com.conde.kun.fija.view.login

import com.conde.kun.core.domain.Resource
import com.conde.kun.core.domain.Status
import com.conde.kun.core.util.isEmailValid
import com.conde.kun.core.util.isPasswordValid
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.fija.domain.usecase.LoginUseCase
import com.conde.kun.fija.domain.model.User

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel<LoginViewState>() {


    override fun getInitialViewState(): LoginViewState {
        return LoginViewState()
    }

    fun login(useraccount: String, password: String) {

        val value = viewState.value ?: getInitialViewState()
        value.error = null
        if (!value.passwordError && !value.useraccountError) {
            viewState.addSource(
                loginUseCase.execute(
                    LoginUseCase.LoginParams(useraccount, password)
                )
            ) { resource: Resource<User> ->

                when (resource.status) {
                    Status.LOADING -> {
                        value.loading = true
                        checkSubmitButton(value)
                    }
                    Status.ERROR -> {
                        value.loading = false
                        value.error = resource.exception
                        checkSubmitButton(value)
                    }
                    Status.SUCCESS -> {
                        value.loading = false
                        value.loginSuccess = true
                    }
                }

                viewState.value = value
            }
        }

        viewState.value = value
    }

    fun onUseraccountChanged(useraccount: String) {
        val value = viewState.value ?: getInitialViewState()
        value.useraccountError = !isEmailValid(useraccount)
        checkSubmitButton(value)
        viewState.value = value
    }

    fun onPasswordChanged(password: String) {
        val value = viewState.value ?: getInitialViewState()
        value.passwordError = !isPasswordValid(password)
        checkSubmitButton(value)
        viewState.value = value
    }

    fun checkSubmitButton(viewState: LoginViewState) {
        viewState.submitButtonEnabled = !viewState.useraccountError
                && !viewState.passwordError
                && !viewState.loading
    }

}