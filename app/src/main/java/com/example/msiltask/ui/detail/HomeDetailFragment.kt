package com.example.msiltask.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.msiltask.R
import com.example.msiltask.data.model.RepoItem
import com.example.msiltask.support.imageLoader.ImageLoader
import com.example.msiltask.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_repo_details.view.*

class HomeDetailFragment : Fragment() {

    private lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: HomeDetailsViewModel
    private lateinit var adapter: CommentAdapter

    companion object {
        const val COMMENTS = "comments"
        const val REPO = "repoDetails"
        fun newInstance(list: ArrayList<String>?, repoItem: RepoItem): Fragment {
            val fragment = HomeDetailFragment()
            val argument = Bundle()
            argument.putStringArrayList(COMMENTS, list)
            argument.putSerializable(REPO, repoItem)
            fragment.arguments = argument
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeDetailsViewModel::class.java)
        viewModel.comments = arguments?.getStringArrayList("comments") ?: emptyList()
        arguments?.getSerializable("repoDetails")?.let {
            if (it is RepoItem)
                viewModel.repoDetails = it
        }
        imageLoader = ImageLoader(requireContext())
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CommentAdapter(viewModel.comments)
        view.rvComment.adapter = adapter
        viewModel.repoDetails?.let {
            imageLoader.DisplayImage(it.owner.avatarUrl, view.ivUserImageDetails)
            view.tvUserNameDetail.text = getString(R.string.name, it.name)
            view.tvFullName.text = getString(R.string.full_name, it.name)
            view.tvOwnerDetails.text = getString(
                R.string.owner_details, it.owner.login,
                it.owner.url, it.owner.htmlUrl, it.owner.followersUrl, it.owner.type
            )

        }
    }


}