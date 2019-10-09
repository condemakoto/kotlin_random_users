package com.conde.kun.fija.view.dashboard

import com.conde.kun.core.domain.BaseResourceObserver
import com.conde.kun.core.domain.Status
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.fija.domain.usecase.LogoutUseCase

class DashboardViewModel(val logoutUseCase: LogoutUseCase) : BaseViewModel<DashboardViewState>() {

    override fun getInitialViewState(): DashboardViewState {
        return DashboardViewState()
    }

    fun logout() {
        BaseResourceObserver(viewState, logoutUseCase.execute(null)) {
            if (it.status == Status.SUCCESS) {
                val vs = viewState.value!!
                vs.goToOnboarding = true
                viewState.value = vs
            }
        }

    }
}

class DashboardViewState {
    var goToOnboarding = false
}