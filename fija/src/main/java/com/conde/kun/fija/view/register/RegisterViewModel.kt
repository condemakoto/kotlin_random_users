package com.conde.kun.fija.view.register

import com.conde.kun.core.domain.Resource
import com.conde.kun.core.domain.Status
import com.conde.kun.core.error.BaseException
import com.conde.kun.core.util.isEmailValid
import com.conde.kun.core.util.isPasswordValid
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.fija.domain.usecase.RegisterUseCase

class RegisterViewModel(val registerUseCase: RegisterUseCase) : BaseViewModel<RegisterViewState>() {

    override fun getInitialViewState(): RegisterViewState {
        return RegisterViewState()
    }

    fun register(email: String, password: String, firstName: String, lastName: String) {

        val value = viewState.value ?: getInitialViewState()
        value.error = null
        if (!value.passwordError && !value.useraccountError) {
            viewState.addSource(
                registerUseCase.execute(
                    RegisterUseCase.Params(email, password, firstName, lastName)
                )
            ) { resource: Resource<Boolean> ->

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
                        value.registerSuccess = true
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

    fun onFirstNameChanged(firstName: String) {
        val value = viewState.value ?: getInitialViewState()
        value.firstNameError = firstName.length < 3
        checkSubmitButton(value)
        viewState.value = value
    }

    fun onLastNameChanged(lastName: String) {
        val value = viewState.value ?: getInitialViewState()
        value.lastNameError = lastName.length < 3
        checkSubmitButton(value)
        viewState.value = value
    }

    fun checkSubmitButton(viewState: RegisterViewState) {
        viewState.submitButtonEnabled = !viewState.useraccountError
                && !viewState.passwordError
                && !viewState.loading
    }
}

class RegisterViewState {
    var loading = false
    var error: BaseException? = null
    var useraccountError = false
    var passwordError = false
    var firstNameError = false
    var lastNameError = false
    var submitButtonEnabled = false
    var registerSuccess = false

}