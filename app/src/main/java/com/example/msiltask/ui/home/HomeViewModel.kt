package com.example.msiltask.ui.home

import androidx.lifecycle.ViewModel
import com.example.msiltask.data.model.RepoItem
import com.example.msiltask.data.repo.HomeRepo

class HomeViewModel : ViewModel() {
    val repo = HomeRepo()
    fun getPublicRepo() = repo.getPublicRepo(page)
    val commentMap = HashMap<String, List<String>>()
    fun addCommentToPost(comment: String, data: RepoItem) {
        commentMap[data.id.toString()] = commentMap[data.id.toString()].orEmpty().plus(comment)


    }

    var isLoading: Boolean = false
    var isLastPage: Boolean = false
    var page = 1
}