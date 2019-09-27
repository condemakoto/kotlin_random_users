package com.conde.kun.randomusers.view.user.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_user_list.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserListFragment : Fragment(), UserAdapter.OnUserSelectListener {

    val IMAGES_PER_ROW = 3
    lateinit var userAdapter: UserAdapter
    lateinit var mLayoutManager: GridLayoutManager

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getViewModel()
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter = UserAdapter(this)
        mLayoutManager = GridLayoutManager(activity, IMAGES_PER_ROW)
        recyclerView.adapter = userAdapter
        recyclerView.layoutManager = mLayoutManager
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        viewModel.onViewInit()
        viewModel.viewState.observe(this, viewStateObserver)

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                checkRefreshCondition()
            }
        })
    }

    fun checkRefreshCondition() {
        val visibleItemCount = recyclerView.childCount;
        val totalItemCount = mLayoutManager.itemCount
        val firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()
        viewModel.checkRefreshCondition(visibleItemCount, totalItemCount, firstVisibleItem)
    }

    val viewStateObserver = Observer<UserListViewState> { viewState ->
        userAdapter.usersList = viewState?.usersList
        swipeRefreshLayout.isRefreshing = viewState?.loading ?: false
        if (viewState?.error ?: false) {
            Snackbar.make(this.view!!, "Error", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onUserSelected(user: User) {
        (activity as FragmentInterface).onUserSelected(user)
    }

    interface FragmentInterface {
        fun onUserSelected(user: User)
    }

}