package com.example.mygithubapps.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygithubapps.activity.FragmentInfo

class SectionsPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    var username: String = ""

    override fun createFragment(position: Int): Fragment {
        val fragments = FragmentInfo()
        fragments.arguments = Bundle().apply {
            putInt(FragmentInfo.ARG_POSITION, position+1)
            putString(FragmentInfo.ARG_USERNAME, username)
        }
        return fragments
    }

    override fun getItemCount(): Int {
        return 2
    }
}