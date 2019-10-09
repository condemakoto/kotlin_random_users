package com.conde.kun.fija.view.splash

import androidx.lifecycle.viewModelScope
import com.conde.kun.core.domain.Status
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.fija.domain.usecase.IsUserSignedInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(val isUserSignedInUseCase: IsUserSignedInUseCase) :
    BaseViewModel<SplashViewState>() {

    override fun getInitialViewState(): SplashViewState {
        return SplashViewState()
    }

    fun onInitView() {
        viewModelScope.launch {
            delay(2000)

            viewState.addSource(isUserSignedInUseCase.execute(null))
            {
                if (it.status != Status.LOADING) {
                    val value = viewState.value!!
                    if (it.data!!) {
                        value.showDashboard = true
                    } else {
                        value.showLogin = true
                    }
                    viewState.value = value
                }
            }
        }

    }
}

class SplashViewState {

    var showLogin = false
    var showDashboard = false

}