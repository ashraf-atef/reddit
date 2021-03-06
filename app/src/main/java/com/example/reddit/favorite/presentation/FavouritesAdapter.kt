package com.example.reddit.favorite.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.reddit.R
import com.example.common.common.presentation.BaseDiffUtilCallback
import com.example.reddit.posts.data.model.PostData
import kotlinx.android.synthetic.main.item_favourite.view.*
import kotlinx.android.synthetic.main.item_post.view.iv_post_image
import kotlinx.android.synthetic.main.item_post.view.iv_video
import kotlinx.android.synthetic.main.item_post.view.tv_post_text

class FavouritesAdapter (
    private val favouritesListener: FavouritesListener
) : RecyclerView.Adapter<FavouritesAdapter.PostViewHolder>() {

    private val list: MutableList<PostData> = mutableListOf()
    private val diff by lazy {
        object : com.example.common.common.presentation.BaseDiffUtilCallback<PostData>(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
               return oldData[oldItemPosition].id == newData[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldData[oldItemPosition] == newData[newItemPosition]
            }

        }
    }

    override fun getItemCount(): Int = list.size

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    fun addData(newPosts: List<PostData>) {
        diff.setLists(list, newPosts)
        val diffResult = DiffUtil.calculateDiff(diff)
        diffResult.dispatchUpdatesTo(this)
        this.list.clear()
        this.list.addAll(newPosts)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.btn_delete_from_favourite.setOnClickListener {
               favouritesListener.deleteFromFavourite(adapterPosition, list[adapterPosition])
            }
        }

        fun bind() {
            with(list[adapterPosition]) {
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

interface FavouritesListener {
    fun deleteFromFavourite(index: Int, post: PostData)
}