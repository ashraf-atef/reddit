package com.example.reddit.posts.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddit.R
import com.example.reddit.posts.data.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter (
    private val postsListener: PostsListener
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {


    private val list: MutableList<Post> = mutableListOf()

    override fun getItemCount(): Int = list.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    fun addData(posts: List<Post>) {
        this.list.clear()
        this.list.addAll(posts)
        notifyDataSetChanged()
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