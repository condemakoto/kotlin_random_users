package com.conde.kun.randomusers.view.user.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conde.kun.randomusers.domain.model.Location
import com.conde.kun.randomusers.domain.model.User
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    val viewState: MutableLiveData<UserListViewState> =
        MutableLiveData<UserListViewState>().apply { postValue(getInitialViewState()) }

    fun getInitialViewState(): UserListViewState {
        val viewState = UserListViewState()
        return viewState
    }

    fun onViewInit() {
        viewModelScope.launch {
            val usersList = ArrayList<User>()
            val user = User(
                "username",
                "title",
                "first name",
                "last name",
                "masculine",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.0-0/p206x206/56848170_2341937865858812_1914529376393756672_n.jpg?_nc_cat=105&_nc_oc=AQnMYXYNFGr0eKD6sz_f2q2wFHhV_vx9paOPofBRXyXybXFlKocEuLv8xiXPDEZgM8c&_nc_ht=scontent.faep9-2.fna&oh=3531dd5b7b04ab1e1f17871f187df311&oe=5E3CB5FA",
                "https://scontent.faep9-2.fna.fbcdn.net/v/t1.0-0/p206x206/56848170_2341937865858812_1914529376393756672_n.jpg?_nc_cat=105&_nc_oc=AQnMYXYNFGr0eKD6sz_f2q2wFHhV_vx9paOPofBRXyXybXFlKocEuLv8xiXPDEZgM8c&_nc_ht=scontent.faep9-2.fna&oh=3531dd5b7b04ab1e1f17871f187df311&oe=5E3CB5FA",
                "lala@hotmail.com",
                "16276127612",
                Location("street", "city", "state", "postCode")
            )
            usersList.add(user)
            usersList.add(user.copy())
            usersList.add(user.copy())
            usersList.add(user.copy())
            usersList.add(user.copy())
            usersList.add(user.copy())

            viewState.value?.usersList = usersList
            viewState.postValue(viewState.value)
        }

    }
}