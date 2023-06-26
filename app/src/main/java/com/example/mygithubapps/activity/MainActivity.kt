package com.example.mygithubapps.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapps.adapter.ListUsersAdapter
import com.example.mygithubapps.databinding.ActivityMainBinding
import com.example.mygithubapps.response.ItemsItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvGithubUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvGithubUser.addItemDecoration(itemDecoration)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        viewModel.username.observe(this){
                userList -> setUserData(userList)
        }

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        binding.userSearch.setOnEditorActionListener(TextView.OnEditorActionListener { view, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                val textSearch = view.text.toString()
                viewModel.searchGithubUser(textSearch)
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })
    }
    private fun setUserData(itemsItem: List<ItemsItem>) {
        val adapter = ListUsersAdapter(itemsItem)
        binding.rvGithubUser.adapter = adapter
        binding.rvGithubUser.visibility = View.VISIBLE
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}