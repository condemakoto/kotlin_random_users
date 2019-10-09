package com.conde.kun.fija.view.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conde.kun.core.domain.BaseError
import com.conde.kun.fija.R
import com.conde.kun.fija.view.base.BaseFragment
import com.conde.kun.fija.view.dashboard.DashboardActivity
import com.conde.kun.fija.view.dialog.ProgressDialog
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment : BaseFragment<LoginViewState, LoginViewModel>() {

    var progressDialog: ProgressDialog? = null

    override fun initViewModel(): LoginViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton.setOnClickListener {
            viewModel.login(
                emailTIL.editText?.text.toString(),
                passwordTIL.editText?.text.toString()
            )
        }
        emailTIL.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onUseraccountChanged(p0.toString())
            }
        })
        passwordTIL.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //do nothing
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onPasswordChanged(p0.toString())
            }

        })
        registerButton.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }
    }

    override fun onViewStateUpdated(viewState: LoginViewState) {

        processError(viewState.error)

        emailTIL.error =
            if (viewState.useraccountError) getString(R.string.username_error) else null
        passwordTIL.error =
            if (viewState.passwordError) getString(R.string.password_error) else null
        submitButton.isEnabled = viewState.submitButtonEnabled
        registerButton.isEnabled = !viewState.loading

        emailTIL.isEnabled = !viewState.loading
        passwordTIL.isEnabled = !viewState.loading

        if (viewState.loginSuccess) {
            startActivity(Intent(context, DashboardActivity::class.java))
        }
    }

    override fun processBusinessError(error: BaseError) {
        if (error.errorCode == 5 || error.errorCode == 4) {
            showSnackbarError(getString(R.string.error_invalid_credentials))
        } else if (error.errorCode == 6) {
            showSnackbarError(getString(R.string.error_user_not_confirmed))
        } else {
            super.processBusinessError(error)
        }
    }

    fun showProgressDialog(show: Boolean) {
        if (show) {
            if (progressDialog == null) {

            }
        } else {

        }
    }

}