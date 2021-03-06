package com.conde.kun.randomusers.view.user.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView = view.userImage
    val userNameView = view.userNameTV
}

class UserAdapter(val onUserSelectListener: UserAdapter.OnUserSelectListener) :
    RecyclerView.Adapter<ViewHolder>() {

    var usersList: List<User>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return usersList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = usersList!![position]
        holder.userNameView.text = user.firstName
        Picasso
            .with(holder.imageView.context)
            .load(user.bigImageUrl)
            .into(holder.imageView)
        holder.itemView.tag = user
        holder.itemView.setOnClickListener { view ->
            view.tag?.let { onUserSelectListener.onUserSelected(it as User) }
        }
    }

    interface OnUserSelectListener {
        fun onUserSelected(user: User)
    }
}