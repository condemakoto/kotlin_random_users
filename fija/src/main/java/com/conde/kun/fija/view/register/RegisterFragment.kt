package com.conde.kun.fija.view.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conde.kun.core.domain.BaseError
import com.conde.kun.core.error.BusinessErrorException
import com.conde.kun.core.error.NoInternetException
import com.conde.kun.core.error.ServerErrorException
import com.conde.kun.fija.R
import com.conde.kun.fija.view.base.BaseFragment
import com.conde.kun.fija.view.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.loginButton
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegisterFragment : BaseFragment<RegisterViewState, RegisterViewModel>() {

    override fun initViewModel(): RegisterViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_register
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailTIL.editText?.addTextChangedListener(CheckTextChangedListener { value ->
            viewModel.onUseraccountChanged(
                value
            )
        })
        passwordTIL.editText?.addTextChangedListener(CheckTextChangedListener { value ->
            viewModel.onPasswordChanged(
                value
            )
        })
        firstNameTIL.editText?.addTextChangedListener(CheckTextChangedListener { value ->
            viewModel.onFirstNameChanged(
                value
            )
        })
        lastNameTIL.editText?.addTextChangedListener(CheckTextChangedListener { value ->
            viewModel.onLastNameChanged(
                value
            )
        })

        loginButton.setOnClickListener { findNavController().popBackStack() }
        submitButton.setOnClickListener {
            viewModel.register(
                emailTIL.editText?.text.toString(),
                passwordTIL.editText?.text.toString(),
                firstNameTIL.editText?.text.toString(),
                lastNameTIL.editText?.text.toString()
            )
        }
    }

    override fun onViewStateUpdated(viewState: RegisterViewState) {
        processError(viewState.error)

        emailTIL.error =
            if (viewState.useraccountError) getString(R.string.username_error) else null
        passwordTIL.error =
            if (viewState.passwordError) getString(R.string.password_error) else null
        submitButton.isEnabled = viewState.submitButtonEnabled
        loginButton.isEnabled = !viewState.loading

        emailTIL.isEnabled = !viewState.loading
        passwordTIL.isEnabled = !viewState.loading
        firstNameTIL.isEnabled = !viewState.loading
        lastNameTIL.isEnabled = !viewState.loading

        if (viewState.registerSuccess) {
            startActivity(Intent(context, DashboardActivity::class.java))
        }
    }

    override fun processBusinessError(error: BaseError) {
        when (error.errorCode) {
            4 -> showSnackbarError(getString(R.string.error_user_already_registered))
            else -> showSnackbarError(getString(R.string.error_server))
        }
    }

}

class CheckTextChangedListener(val onChanged: (String) -> Unit) : TextWatcher {

    override fun afterTextChanged(p0: Editable?) {
        //do nothing
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //do nothing
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onChanged(p0.toString())
    }
}