package com.conde.kun.randomusers.view.user.userlist

import androidx.lifecycle.*
import com.conde.kun.core.domain.Resource
import com.conde.kun.core.domain.Status
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.usecase.GetUserUseCase

class UserListViewModel : ViewModel() {

    val viewState: MediatorLiveData<UserListViewState> =
        MediatorLiveData<UserListViewState>().apply { postValue(getInitialViewState()) }
    var pageNum: Int = 1

    private val getUserUseCase: GetUserUseCase = GetUserUseCase(viewModelScope)

    private fun getInitialViewState(): UserListViewState {
        val viewState = UserListViewState()
        viewState.loading = true
        viewState.error = false
        return viewState
    }

    fun onViewInit() {
        pageNum = 1
        retrieveUsers()
    }

    fun onRefresh() {
        pageNum = 1
        retrieveUsers()
    }

    private fun retrieveUsers() {

        viewState.addSource(getUserUseCase.execute(GetUserUseCase.Param(pageNum))) { resource: Resource<List<User>> ->
            val value = viewState.value ?: getInitialViewState()
            when (resource.status) {
                Status.LOADING -> value.loading = true
                Status.SUCCESS -> {
                    value.loading = false
                    value.usersList = resource.data
                }
                Status.ERROR -> {
                    value.loading = false
                    value.error = true
                }
            }
            viewState.value = value
        }
    }
}