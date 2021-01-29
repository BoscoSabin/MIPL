package com.example.msiltask.ui.detail

import androidx.lifecycle.ViewModel
import com.example.msiltask.data.model.RepoItem
import java.util.ArrayList

class HomeDetailsViewModel() : ViewModel() {

    lateinit var comments: List<String>
    var repoDetails: RepoItem? = null
}