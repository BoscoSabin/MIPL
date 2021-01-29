package com.example.msiltask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.msiltask.ui.home.FragmentAdapter
import com.example.msiltask.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager(viewPager)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.currentItem = 0
    }

    private fun setupViewPager(viewPager: ViewPager) {
        viewPagerAdapter = FragmentAdapter(supportFragmentManager)

        viewPagerAdapter.addFragment(HomeFragment.newInstance("Home"), "Home")
        viewPagerAdapter.addFragment(HomeFragment.newInstance("Dashboard"), "Dashboard")
        viewPagerAdapter.addFragment(HomeFragment.newInstance("Notifications"), "Notifications")
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.count
    }

    private lateinit var viewPagerAdapter: FragmentAdapter
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    viewPager.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onBackPressed() {
        (viewPagerAdapter.getFragment(viewPager.currentItem) as HomeFragment).let {
            if (!it.backStack()) {
                super.onBackPressed()

            }
        }
    }

}