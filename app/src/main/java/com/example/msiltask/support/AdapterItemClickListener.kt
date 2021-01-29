package com.example.msiltask.support

import com.example.msiltask.data.model.RepoItem

interface AdapterItemClickListener {
  fun  onItemClick(pos:Int,item:RepoItem)
}