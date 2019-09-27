package com.conde.kun.randomusers.view.user.userdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    companion object {
        val KEY_USER = "user"

        fun getIntent(context: Context, user: User): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(KEY_USER, user)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setSupportActionBar(toolbar)

        val user: User = intent.getParcelableExtra(KEY_USER)
        Picasso.with(this)
            .load(user.bigImageUrl)
            .placeholder(R.drawable.default_avatar_large)
            .into(imageView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UserDetailAdapter(false, user)

        supportActionBar?.title = getString(R.string.txtUserDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}