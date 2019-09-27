package com.conde.kun.randomusers.view.user.userdetail

import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.core.view.BaseViewState
import com.conde.kun.randomusers.domain.model.User

class UserDetailViewState(var user: User? = null): BaseViewState()

class UserDetailViewModel: BaseViewModel<UserDetailViewState>() {

    override fun getInitialViewState(): UserDetailViewState {
        return UserDetailViewState()
    }

    fun onViewCreated(user: User?) {
        viewState.value?.user = user
        viewState.value = viewState.value
    }

}