package com.conde.kun.randomusers.view.user.userlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conde.kun.core.view.BaseMVVMFragment
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserListFragment : BaseMVVMFragment<UserListViewState, UserListViewModel> (), UserAdapter.OnUserSelectListener {

    val IMAGES_PER_ROW = 3
    lateinit var userAdapter: UserAdapter
    lateinit var mLayoutManager: GridLayoutManager

    override fun getLayoutResource(): Int {
        return R.layout.fragment_user_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(this)
        mLayoutManager = GridLayoutManager(activity, IMAGES_PER_ROW)
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = mLayoutManager
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        viewModel.onViewInit()
        viewModel.showUserDetail.observe(this, userDetailObserver)

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                checkRefreshCondition()
            }
        })
    }

    fun checkRefreshCondition() {
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = mLayoutManager.itemCount
        val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
        viewModel.checkRefreshCondition(visibleItemCount, totalItemCount, firstVisibleItem)
    }


    override fun initViewModel(): UserListViewModel {
        return getViewModel()
    }

    override fun onViewStateUpdated(viewState: UserListViewState) {
        userAdapter.usersList = viewState.usersList
        swipeRefreshLayout.isRefreshing = viewState.loading
        if (viewState.error) {
            Snackbar.make(this.view!!, "Error", Snackbar.LENGTH_LONG).show()
        }
    }

    private val userDetailObserver = Observer<User> { user ->
        user?.let{(activity as FragmentInterface).onUserSelected(it)}
    }

    override fun onUserSelected(user: User) {
        viewModel.onUserSelected(user)
    }

    interface FragmentInterface {
        fun onUserSelected(user: User)
    }

}