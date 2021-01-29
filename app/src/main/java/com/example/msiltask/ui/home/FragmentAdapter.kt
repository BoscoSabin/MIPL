package com.example.msiltask.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class FragmentAdapter(manager: FragmentManager) : FragmentPagerAdapter(
    manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    fun getFragment(position: Int):Fragment{
        return mFragmentList.get(position)
    }
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val titles: MutableList<String> = ArrayList()
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}