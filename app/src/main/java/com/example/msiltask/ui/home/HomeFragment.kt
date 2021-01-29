package com.example.msiltask.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msiltask.R
import com.example.msiltask.data.model.RepoItem
import com.example.msiltask.support.PaginationListener
import com.example.msiltask.support.RepoItemClickListener
import com.example.msiltask.ui.detail.HomeDetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), RepoItemClickListener {

    private val TAG: String = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeRepoAdapter


    companion object {
        const val KEY = "FragmentKey"
        fun newInstance(key: String): Fragment {
            val fragment = HomeFragment()
            val argument = Bundle()
            argument.putString(KEY, key)
            fragment.arguments = argument
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeRepoAdapter(this)
        view.rvRepo.adapter = adapter
        getRepo()
        view.rvRepo.addOnScrollListener(paginationListener)

    }

    private fun getRepo() {
        homeViewModel.getPublicRepo().observe(viewLifecycleOwner, { result ->
            progressBarHome.isVisible = false
            homeViewModel.isLoading = false
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    adapter.addRepo(it.items)
                }
            } else if (result.isFailure) {
                showToast("${result.exceptionOrNull()?.message}")
            }

        })
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    var paginationListener: PaginationListener = object : PaginationListener() {

        override val isLoading: Boolean
            get() = homeViewModel.isLoading

        override fun loadMoreItems() {
            homeViewModel.isLoading = true
            homeViewModel.page++
            progressBarHome.isVisible = false
            getRepo()

        }


        override fun getLayoutManager(): LinearLayoutManager {
            return rvRepo.layoutManager as LinearLayoutManager
        }
    }

    override fun onAddComment(comment: String, pos: Int, data: RepoItem) {
        homeViewModel.addCommentToPost(comment, data)
        showToast(getString(R.string.comment_added))
        Log.d(TAG, "onAddComment: ${homeViewModel.commentMap[data.id.toString()]}")

    }

    override fun onItemClick(pos: Int, item: RepoItem) {
        createFragment(item, homeViewModel.commentMap[item.id.toString()])
    }

    private fun createFragment(item: RepoItem, comments: List<String?>?) {
        val fragment = HomeDetailFragment.newInstance(comments as ArrayList<String>?, item)
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, "homeDetails")
            .commit()
    }

    fun removeFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()

    }

    fun backStack(): Boolean {
        (childFragmentManager.findFragmentByTag("homeDetails"))?.let {
            removeFragment(it)
            return true
        }
        return false

    }

}