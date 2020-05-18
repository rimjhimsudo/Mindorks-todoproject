package com.example.todoapp.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return OnBoardingOneFragment()
            1 -> return OnBoardingtwoFragment()
        }

        throw IllegalStateException("position $position is invalid for this viewpager")
    }

    override fun getCount(): Int {
        return 2
    }

}