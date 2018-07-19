package com.eatchicken.go.utils

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.eatchicken.go.R

class RecyclerViewLoadMoreHelper(recyclerView: RecyclerView, private val swipeRefreshLayout: SwipeRefreshLayout?, val scrollListener: Listener?) {
    private var isLoading = true
    private var hasMore = false
    var pageIndex = 0
    var pageSize = 20

    init {
        swipeRefreshLayout?.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout?.setOnRefreshListener {
            pageIndex = 0
            isLoading = true
            scrollListener?.onRefreshListener()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItem = (recyclerView?.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val visibleItemCount = recyclerView.childCount
                val totalItemCount = recyclerView.layoutManager.itemCount
                if (dy >= 0 && !isLoading && (firstVisibleItem + visibleItemCount) >= totalItemCount - 2 && hasMore && pageIndex > 0) {
                    isLoading = true
                    scrollListener?.onStartLoadMore()
                }
            }
        })
    }

    fun startSwipeRefresh() {
        swipeRefreshLayout?.isRefreshing = true
        pageIndex = 0
        isLoading = true
        scrollListener?.onRefreshListener()
    }

    fun loadCompleted(loadedItemCount: Int) {
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout.isRefreshing = false
        }
        hasMore = loadedItemCount >= pageSize
        isLoading = false
        pageIndex++
    }

    fun loadFailed() {
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout.isRefreshing = false
        }
        isLoading = false
    }


    interface Listener {
        fun onRefreshListener()

        fun onStartLoadMore()
    }

}