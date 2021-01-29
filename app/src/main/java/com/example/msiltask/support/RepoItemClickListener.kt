package com.example.msiltask.support

import com.example.msiltask.data.model.RepoItem

interface RepoItemClickListener : AdapterItemClickListener {
    fun onAddComment(comment: String, pos: Int, data: RepoItem)
}