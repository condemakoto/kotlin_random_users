package com.conde.kun.usersupport.view

import android.text.TextUtils
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.core.view.BaseViewState

class ContactFormViewState :
    BaseViewState() {

    var loading = false
    var success = false
    var error = false

    var nameError = false
    var contactNumberError = false
    var emailError = false
    var subjectError = false
    var messageError = false
}

class ContactFormViewModel : BaseViewModel<ContactFormViewState>() {

    override fun getInitialViewState(): ContactFormViewState {
        return ContactFormViewState()
    }

    fun onSubmitForm(
        name: String,
        contactNumber: String,
        email: String,
        subject: String,
        message: String
    ) {
        val vs = viewState.value!!
        vs.nameError = !isFieldValid(name)
        vs.contactNumberError = !isFieldValid(contactNumber)
        vs.emailError = !isEmailValid(email)
        vs.subjectError = !isFieldValid(subject)
        vs.messageError = !isFieldValid(message)

        if (vs.nameError || vs.contactNumberError || vs.emailError || vs.subjectError || vs.messageError) {
            viewState.value = vs
            return
        }

    }

    private fun isFieldValid(field: String): Boolean {
        return (field.length in 1..255)
    }

    private fun isEmailValid(email: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            return false
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}