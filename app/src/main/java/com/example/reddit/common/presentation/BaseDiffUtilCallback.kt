package com.example.reddit.common.presentation

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallback<T> : DiffUtil.Callback() {
    protected var oldData: List<T> =  listOf()
    protected var newData: List<T> = listOf()

    fun setLists(oldData: List<T>, newData: List<T>) {
        this.oldData = oldData
        this.newData = newData
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return newData
    }
}