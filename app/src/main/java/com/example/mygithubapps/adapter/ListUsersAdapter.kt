package com.example.mygithubapps.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubapps.activity.DetailActivity
import com.example.mygithubapps.R
import com.example.mygithubapps.response.ItemsItem

class ListUsersAdapter(private val listUserGithub: List<ItemsItem>): RecyclerView.Adapter<ListUsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_users, viewGroup, false))
    }

    override fun getItemCount() = listUserGithub.size
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName : TextView = view.findViewById(R.id.nama_users)
        val imgPhoto : ImageView = view.findViewById(R.id.img_user_avatar)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gitUsers = listUserGithub[position]
        holder.textName.text = gitUsers.login
        Glide.with(holder.itemView.context)
            .load(gitUsers.avatarUrl)
            .circleCrop()
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener{
            val intentDetailUser = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetailUser.putExtra(DetailActivity.EXTRA_USER, gitUsers.login)
            holder.itemView.context.startActivity(intentDetailUser)
        }
    }


}