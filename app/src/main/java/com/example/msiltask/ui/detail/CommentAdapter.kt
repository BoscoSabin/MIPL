package com.example.msiltask.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.msiltask.R
import com.example.msiltask.data.model.RepoItem
import com.example.msiltask.support.RepoItemClickListener
import com.example.msiltask.support.imageLoader.ImageLoader
import kotlinx.android.synthetic.main.support_comment.view.*
import kotlinx.android.synthetic.main.support_list_item_repo.view.*

class CommentAdapter(val  comments:List<String>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.support_comment, parent, false)
        ))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = comments.size




    inner class ViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(position: Int) {
            viewItem.tvComment.text=comments[position]
        }




    }
}