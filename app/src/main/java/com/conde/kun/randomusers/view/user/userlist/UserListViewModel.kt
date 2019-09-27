package com.conde.kun.randomusers.view.user.userlist

import android.util.Log
import androidx.lifecycle.*
import com.conde.kun.core.domain.Resource
import com.conde.kun.core.domain.Status
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.usecase.GetUserUseCase

class UserListViewModel(val getUserUseCase: GetUserUseCase) : ViewModel() {

    val viewState: MediatorLiveData<UserListViewState> =
        MediatorLiveData<UserListViewState>().apply { postValue(getInitialViewState()) }
    var pageNum: Int = 1
    var loading = false
    val VISIBLE_THRESHOLD = 2

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

    fun checkRefreshCondition(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if (pageNum > 1 && !loading && totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
            retrieveUsers()
        }
    }

    private fun retrieveUsers() {

        viewState.addSource(getUserUseCase.execute(viewModelScope, GetUserUseCase.Param(pageNum))) { resource: Resource<List<User>> ->
            val value = viewState.value ?: getInitialViewState()
            when (resource.status) {
                Status.LOADING -> value.loading = true
                Status.SUCCESS -> {
                    value.loading = false
                    if (pageNum == 1) {
                        value.usersList = resource.data
                    } else {
                        val newList = ArrayList<User>()
                        newList.addAll(value.usersList!!)
                        newList.addAll(resource.data!!)
                        value.usersList = newList
                    }
                    pageNum++
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