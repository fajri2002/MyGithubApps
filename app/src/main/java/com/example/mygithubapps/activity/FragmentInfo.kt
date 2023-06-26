package com.example.mygithubapps.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapps.adapter.ListUsersAdapter
import com.example.mygithubapps.databinding.FragmentInfoBinding
import com.example.mygithubapps.response.ItemsItem

class FragmentInfo: Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var detailUserViewModel: DetailViewModel

    companion object {
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = 0
        var username = arguments?.getString(ARG_USERNAME)

        Log.d("arguments: position", position.toString())
        Log.d("arguments: username", username.toString())

        detailUserViewModel =
            ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(
                DetailViewModel::class.java
            )
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }

        if (position == 1) {
            showLoadingUser(true)
            username?.let { detailUserViewModel.getFollowers(it) }
            detailUserViewModel.userFollowers.observe(viewLifecycleOwner) {
                setFollowData(it)
                showLoadingUser(false)
            }
        } else {
            showLoadingUser(true)
            username?.let { detailUserViewModel.getFollowing(it) }
            detailUserViewModel.userFollowings.observe(viewLifecycleOwner) {
                setFollowData(it)
                showLoadingUser(false)
            }
        }
    }

    private fun showLoadingUser(isLoading: Boolean) {
        if (isLoading) {
            binding.loadingDetailFollow.visibility = View.VISIBLE
        } else {
            binding.loadingDetailFollow.visibility = View.GONE
        }
    }

    private fun setFollowData(listFollow: List<ItemsItem>) {
        binding.apply {
            binding.rvDetailFollow.layoutManager = LinearLayoutManager(requireActivity())
            val adapter = ListUsersAdapter(listFollow)
            binding.rvDetailFollow.adapter = adapter
        }
    }
}