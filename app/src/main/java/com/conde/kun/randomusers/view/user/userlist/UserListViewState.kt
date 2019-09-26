package com.conde.kun.randomusers.view.user.userlist

import com.conde.kun.randomusers.domain.model.User

class UserListViewState {
    var usersList: List<User>? = null
    var loading = false
    var error = false
}