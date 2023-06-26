package com.example.mygithubapps.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mygithubapps.R
import com.example.mygithubapps.adapter.SectionsPagerAdapter
import com.example.mygithubapps.databinding.ActivityDetailBinding
import com.example.mygithubapps.response.DetailUserResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "keyUser"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USER)
        val bundle = Bundle()
        bundle.putString(EXTRA_USER, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)

        val userLogin = intent.getStringExtra(EXTRA_USER)
        binding.tvName.text = userLogin

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = userLogin.toString()

        val viewPager: ViewPager2 = findViewById(R.id.detail_viewpager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tab_followlists)
        TabLayoutMediator(tabs, viewPager) { tabs, position ->
            tabs.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        if (userLogin != null) {
            showLoading(true)
            viewModel.getUserDetail(userLogin)
            showLoading(false)
        }
        viewModel.detailUsers.observe(this) {
                detailUsers -> setDetailUsers(detailUsers)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setDetailUsers(Name: DetailUserResponse) {
        Glide.with(this)
            .load(Name.avatarUrl)
            .into(binding.detailUserPhoto)
        binding.tvName.text = Name.login
        binding.tvUsers.text = Name.name
        binding.tvFollowerCounts.text = Name.followers.toString()
        binding.tvFollowingCounts.text = Name.following.toString()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingDetail.visibility = View.VISIBLE
        } else {
            binding.loadingDetail.visibility = View.GONE
        }
    }

}