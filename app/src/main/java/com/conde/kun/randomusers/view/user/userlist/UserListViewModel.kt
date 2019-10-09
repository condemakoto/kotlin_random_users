package com.conde.kun.randomusers.view.user.userlist

import androidx.lifecycle.*
import com.conde.kun.core.domain.Resource
import com.conde.kun.core.domain.Status
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.domain.usecase.GetUserUseCase
import com.conde.kun.randomusers.domain.usecase.StoreUserSelectionCountUseCase

class UserListViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val storeUserSelectionCountUseCase: StoreUserSelectionCountUseCase
) : BaseViewModel<UserListViewState>() {

    var pageNum: Int = 1
    var loading = false
    var loadingUserInfo = false
    val VISIBLE_THRESHOLD = 2
    val showUserDetail by lazy { MutableLiveData<User>() }

    override fun getInitialViewState(): UserListViewState {
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

        viewState.addSource(
            getUserUseCase.execute(
                GetUserUseCase.Param(pageNum)
            )
        ) { resource: Resource<List<User>> ->
            val value = viewState.value ?: getInitialViewState()
            when (resource.status) {
                Status.LOADING -> loading = true
                Status.SUCCESS -> {
                    loading = false
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
                    loading = false
                    value.error = true
                }
            }
            value.loading = loading || loadingUserInfo
            viewState.value = value
        }
    }

    fun onUserSelected(user: User) {
        viewState.addSource(storeUserSelectionCountUseCase.execute(user)) {
            resource: Resource<Unit> ->
            val value = viewState.value ?: getInitialViewState()
            when (resource.status) {
                Status.LOADING -> loadingUserInfo = true
                Status.SUCCESS -> {
                    value.error = false
                    loadingUserInfo = false
                    showUserDetail.value = user
                }
                Status.ERROR -> {
                    loadingUserInfo = false
                    value.error = true
                }
            }
            value.loading = loadingUserInfo || loading
            viewState.value = value
        }
    }
}