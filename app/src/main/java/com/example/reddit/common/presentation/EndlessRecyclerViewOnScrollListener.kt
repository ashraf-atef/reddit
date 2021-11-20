package com.example.reddit.common.presentation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewOnScrollListener : RecyclerView.OnScrollListener() {

    private var total = 0
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // Scroll vertical to the end
        if (dy > 0) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = recyclerView.layoutManager!!.itemCount
            val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (isLoading) {
                if (totalItemCount > total) {
                    isLoading = false
                    total = totalItemCount
                }
            }

            val visibleThreshold = 5
            // Check if the end has been reached before 5 items (visibleThreshold)
            if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                onLoadMore()
                isLoading = true
            }
        }
    }

    abstract fun onLoadMore()

    fun restState() {
        total = 0
        isLoading = true
    }
}