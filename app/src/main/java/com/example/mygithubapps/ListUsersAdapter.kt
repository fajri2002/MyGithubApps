package com.example.mygithubapps

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

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


    }


}