package com.conde.kun.randomusers.view.user.userdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import com.squareup.picasso.Picasso

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView? = view.findViewById(R.id.imageView)
    val title: TextView? = view.findViewById(R.id.titleTV)
    val detail: TextView? = view.findViewById(R.id.detailTV)
    val divider: View? = view.findViewById(R.id.divider)
}

class UserDetailAdapter(val withPicture: Boolean, val user: User) :
    RecyclerView.Adapter<ViewHolder>() {

    val PICTURE_POSITION = 0
    val USERNAME_POSITION = 1
    val FIRST_NAME_POSITION = 2
    val LAST_NAME_POSITION = 3
    val EMAIL_POSITION = 4

    val VIEW_TYPE_PICTURE = 1
    val VIEW_TYPE_DEFAULT = 0

    val titles =
        listOf(R.string.txtUsername, R.string.txtFirstName, R.string.txtLastName, R.string.txtEmail)
    val values = listOf(user.username, user.firstName, user.lastName, user.email)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = if (viewType == VIEW_TYPE_PICTURE) {
            inflater.inflate(R.layout.item_user_detail_image, parent, false)
        } else {
            inflater.inflate(R.layout.item_user_detail_attribute, parent, false)
        }
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (withPicture) 5 else 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.itemView.context
        var arrayPosition = position

        if (withPicture) {
            if (position == 0) {
                Picasso.with(context)
                    .load(user.bigImageUrl)
                    .placeholder(R.drawable.default_avatar_large)
                    .into(holder.imageView)
            } else {
                arrayPosition--
            }
        }

        holder.title?.text = context.getString(titles[arrayPosition])
        holder.detail?.text = values[arrayPosition]
        holder.divider?.visibility = if (arrayPosition + 1 == itemCount) View.GONE else View.VISIBLE
    }

    override fun getItemViewType(position: Int): Int {
        return if (withPicture && position == PICTURE_POSITION) VIEW_TYPE_PICTURE else VIEW_TYPE_DEFAULT
    }
}