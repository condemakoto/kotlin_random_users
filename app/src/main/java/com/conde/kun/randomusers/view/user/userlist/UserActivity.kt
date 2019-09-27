package com.conde.kun.randomusers.view.user.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.conde.kun.randomusers.view.user.userdetail.UserDetailActivity

class UserActivity : AppCompatActivity(), UserListFragment.FragmentInterface {

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, UserActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportActionBar?.title = getString(R.string.txtUserList)
        if (supportFragmentManager.findFragmentById(R.id.viewContainer) == null) {
            loadUserListView()
        }
    }

    fun loadUserListView() {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.viewContainer, UserListFragment())
        ft.commit()
    }

    override fun onUserSelected(user: User) {
        startActivity(UserDetailActivity.getIntent(this, user))
    }

}