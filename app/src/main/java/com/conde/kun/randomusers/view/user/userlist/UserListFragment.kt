package com.conde.kun.randomusers.view.user.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.conde.kun.randomusers.R
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment: Fragment() {

    val IMAGES_PER_ROW = 3
    lateinit var userAdapter: UserAdapter
    lateinit var mLayoutManager: GridLayoutManager
    val viewModel: UserListViewModel by lazy { ViewModelProviders.of(this).get(UserListViewModel::class.java)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter()
        mLayoutManager = GridLayoutManager(activity, IMAGES_PER_ROW)
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = mLayoutManager
        //swipeRefreshLayout.setOnRefreshListener {  }
        viewModel.onViewInit()
        viewModel.viewState.observe(this, viewStateObserver)
    }

    val viewStateObserver = Observer<UserListViewState> {
        viewState ->
            userAdapter.usersList = viewState?.usersList
    }

}