package com.example.msiltask.support

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener : RecyclerView.OnScrollListener() {

    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var pastVisiblesItems: Int = 0

    protected abstract val isLoading: Boolean


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            visibleItemCount = getLayoutManager().childCount
            totalItemCount = getLayoutManager().itemCount
            pastVisiblesItems = getLayoutManager().findFirstVisibleItemPosition()
            if (/*!isLastPage && */!isLoading && visibleItemCount + pastVisiblesItems >= totalItemCount) {
                loadMoreItems()
            }

        }
    }

    protected abstract fun loadMoreItems()
    //    protected abstract fun noMoreItems()
    protected abstract fun getLayoutManager(): LinearLayoutManager
}