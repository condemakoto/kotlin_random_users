package com.conde.kun.randomusers.view.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.view.user.userlist.UserListFragment

class UserActivity : AppCompatActivity() {

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


}