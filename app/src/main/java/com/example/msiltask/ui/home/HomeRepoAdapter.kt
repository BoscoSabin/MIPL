package com.example.msiltask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.msiltask.R
import com.example.msiltask.data.model.RepoItem
import com.example.msiltask.support.RepoItemClickListener
import com.example.msiltask.support.imageLoader.ImageLoader
import kotlinx.android.synthetic.main.support_list_item_repo.view.*

class HomeRepoAdapter(val repoItemClickListener: RepoItemClickListener) :
    RecyclerView.Adapter<HomeRepoAdapter.ViewHolder>() {
    lateinit var imageLoader: ImageLoader
    val repoList = ArrayList<RepoItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        imageLoader = ImageLoader(parent.context)
        return (ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.support_list_item_repo, parent, false)
        ))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = repoList.size

    fun addRepo(list: List<RepoItem>) {
        repoList.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val viewItem: View) : RecyclerView.ViewHolder(viewItem),
        View.OnClickListener {
        init {

            viewItem.rootItem.setOnClickListener(this)
            viewItem.btnAddComment.setOnClickListener(this)
        }

        fun bind(position: Int) {
            val item = repoList[position]
            viewItem.edtComment.setText("")
            viewItem.tvRepoUser.text = item.name
            imageLoader.DisplayImage(item.owner.avatarUrl,viewItem.ivUserImage)
        }

        override fun onClick(view: View?) {

            when (view?.id) {
                R.id.rootItem -> {
                    repoItemClickListener.onItemClick(adapterPosition, repoList[adapterPosition])
                }
                R.id.btnAddComment -> {
                    val cmt = viewItem.edtComment.text.toString()
                    if (cmt.isNotBlank()) {
                        repoItemClickListener.onAddComment(
                            cmt,
                            adapterPosition,
                            repoList[adapterPosition]
                        )
                        viewItem.edtComment.setText("")
                    }
                }
            }

        }


    }
}