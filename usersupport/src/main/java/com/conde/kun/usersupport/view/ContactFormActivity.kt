package com.conde.kun.usersupport.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.conde.kun.randomusers.usersupport.R
//import com.conde.kun.randomusers.R
import kotlinx.android.synthetic.main.activity_contact_form.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactFormActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_form)
        viewModel = getViewModel()

        submitButton.setOnClickListener { onSubmitClicked() }
        viewModel.viewState.observe(this, viewStateObserver)
    }

    private fun onSubmitClicked() {
        viewModel.onSubmitForm(
            nameTIL.editText?.text.toString(),
            contactNumberTIL.editText?.text.toString(),
            emailTIL.editText?.text.toString(),
            subjectTIL.editText?.text.toString(),
            messageTIL.editText?.text.toString()
        )
    }

    val viewStateObserver = Observer<ContactFormViewState> { viewState ->
        nameTIL.error = getFieldError(viewState.nameError, com.conde.kun.randomusers.R.string.field_name)
        contactNumberTIL.error =
            getFieldError(viewState.contactNumberError, com.conde.kun.randomusers.R.string.field_contact_number)
        emailTIL.error = getFieldError(viewState.emailError, com.conde.kun.randomusers.R.string.field_email)
        subjectTIL.error = getFieldError(viewState.subjectError, com.conde.kun.randomusers.R.string.field_subject)
        messageTIL.error = getFieldError(viewState.messageError, com.conde.kun.randomusers.R.string.field_message)
    }

    fun getFieldError(withError: Boolean, fieldNameStringResource: Int): String? {
        if (withError) {
            return getString(com.conde.kun.randomusers.R.string.field_error, fieldNameStringResource)
        }
        return null
    }


}