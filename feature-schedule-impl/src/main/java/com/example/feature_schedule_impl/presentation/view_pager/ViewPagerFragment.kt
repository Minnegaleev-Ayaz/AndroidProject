package com.example.feature_schedule_impl.presentation.view_pager

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature_schedule_impl.R
import com.example.feature_schedule_impl.databinding.FragmentViewPagerBinding
import com.example.feature_schedule_impl.presentation.ui.schedule.ScheduleFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nefrit.common.base.BaseFragment

class ViewPagerFragment: BaseFragment(R.layout.fragment_view_pager) {
    private val viewBinding: FragmentViewPagerBinding by viewBinding(FragmentViewPagerBinding::bind)
    private val gameList = listOf(past, running, upcoming)
    override fun initViews() {
    }

    override fun inject() {
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = object : FragmentStateAdapter(requireActivity()) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int) =
                ScheduleFragment.newInstance(gameList[position])
        }

        viewBinding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(viewBinding.tabLayout, viewBinding.viewPager) { tab, position ->
            // Set the tab title if needed
            // tab.text = "Tab $position"
        }.attach()
    }
    companion object{
        const val past = "past"
        const val running = "running"
        const val upcoming = "upcoming"
    }
}