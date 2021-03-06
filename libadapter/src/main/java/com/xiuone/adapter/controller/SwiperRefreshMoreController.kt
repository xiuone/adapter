package com.xiuone.adapter.controller

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xiuone.adapter.adapter.RecyclerBaseAdapter
import com.xiuone.adapter.listener.RefreshMoreImpl

class SwiperRefreshMoreController <T> : SwipeRefreshLayout.OnRefreshListener{
    private var pullTo:SwipeRefreshLayout?=null
    private var listener: RefreshMoreImpl?=null
    private var adapter:RecyclerBaseAdapter<T>?=null
    constructor(pullTo:SwipeRefreshLayout?,recyclerView: RecyclerView?,adapter:RecyclerBaseAdapter<T>,listener:RefreshMoreImpl){
        pullTo?.setOnRefreshListener(this)
        this.adapter = adapter
        this.listener = listener
        this.pullTo = pullTo
        recyclerView?.adapter = adapter
    }

    fun getAdapter():RecyclerBaseAdapter<T>? = adapter

    /**
     * 自动刷新
     */
    fun autoRefresh(){
        pullTo?.isRefreshing = true
        listener?.getData(0,0)
    }

    /**
     * 设置数据
     */
    fun setData(data:MutableList<T>?){
        pullTo?.isRefreshing = false
        adapter?.dataController?.setNewData(data)
    }


    override fun onRefresh() {
        listener?.getData(0,0)
    }
}