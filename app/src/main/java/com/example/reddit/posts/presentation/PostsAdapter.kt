package com.example.reddit.posts.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddit.R
import com.example.reddit.common.presentation.BaseDiffUtilCallback
import com.example.reddit.posts.data.model.Post
import com.example.reddit.posts.data.model.PostData
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter (
    private val postsListener: PostsListener
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val list: MutableList<Post> = mutableListOf()
    private val diff by lazy {
        object : BaseDiffUtilCallback<Post>(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition].data.id == newData[newItemPosition].data.id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition] == newData[newItemPosition]
            }

        }
    }

    override fun getItemCount(): Int = list.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    fun addData(newPosts: List<Post>) {
        diff.setLists(list, newPosts)
        val diffResult = DiffUtil.calculateDiff(diff)
        diffResult.dispatchUpdatesTo(this)
        this.list.clear()
        this.list.addAll(newPosts)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.btn_add_to_favourite.setOnClickListener {
               postsListener.addToFavourite(adapterPosition, list[adapterPosition])
            }
        }

        fun bind() {
            with(list[adapterPosition].data) {
                with(itemView) {
                Glide.with(context)
                    .load(thumbnail)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(iv_post_image)
                    tv_post_text.text = title
                    iv_video.isVisible = isVideo
                }
            }
        }
    }
}

interface PostsListener {
    fun addToFavourite(index: Int, post: Post)
}