package com.conde.kun.fija.view.login

import com.conde.kun.core.error.BaseException

class LoginViewState {

    var loading = false
    var error: BaseException? = null
    var useraccountError = false
    var passwordError = false
    var submitButtonEnabled = false
    var loginSuccess = false

}