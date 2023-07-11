package com.athar.suitmediatestathar

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.athar.suitmediatestathar.databinding.ItemUserBinding
import com.bumptech.glide.Glide

class ListUserAdapter : PagingDataAdapter<DataItem, ListUserAdapter.MyViewHolder>(DIFF_CALLBACK){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.tvFirstName.text = data.firstName
            binding.tvLastName.text = data.lastName
            binding.tvEmail.text = data.email
            Glide.with(itemView.context)
                .load(data.avatar)
                .fitCenter()
                .into(binding.imAvatar)

            binding.itemUser.setOnClickListener {
                val detail = Intent(itemView.context, MainActivity::class.java)
                detail.putExtra("Name", data.firstName + data.lastName)
                detail.putExtra("Email", data.email)
                detail.putExtra("Avatar", data.avatar)
                itemView.context.startActivity(
                    detail,
                )
                (binding.root.context as Activity).finishAffinity()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}