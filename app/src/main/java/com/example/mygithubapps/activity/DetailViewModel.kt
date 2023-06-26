package com.example.mygithubapps.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapps.network.ApiConfig
import com.example.mygithubapps.response.DetailUserResponse
import com.example.mygithubapps.response.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel : ViewModel() {

    private val detail_users = MutableLiveData<DetailUserResponse>()
    val detailUsers: LiveData<DetailUserResponse> = detail_users

    private val is_loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = is_loading

    private val user_followers = MutableLiveData<List<ItemsItem>>()
    val userFollowers: LiveData<List<ItemsItem>> = user_followers

    private val user_followings = MutableLiveData<List<ItemsItem>>()
    val userFollowings: LiveData<List<ItemsItem>> = user_followings

    companion object {
        const val TAGS = "DetailViewModel"
    }

    init {
        getUserDetail()
    }

    fun getUserDetail(query: String = "") {
        is_loading.value = true
        val client = ApiConfig.getApiService().getDetailUser(query)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>,
            ) {
                is_loading.value = false
                if (response.isSuccessful) {
                    detail_users.value = response.body()
                } else {
                    Log.d(TAGS, "onFailure: ${response.message()}111")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                is_loading.value = false
                Log.e(TAGS, "onFailure = ${t.message.toString()}" )
            }
        })
    }

    fun getFollowers(query: String = "") {
        is_loading.value = true
        val client = ApiConfig.getApiService().getFollowers(query)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                is_loading.value = false
                if (response.isSuccessful) {
                    user_followers.value = response.body()
                } else {
                    Log.d(TAGS, "onFailure: ${response.message()}222")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                is_loading.value = false
                Log.e(TAGS, "onFailure = ${t.message.toString()}" )
            }
        })

    }

    fun getFollowing(query: String = "") {
        is_loading.value = true
        val client = ApiConfig.getApiService().getFollowings(query)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                is_loading.value = false
                if (response.isSuccessful) {
                    user_followings.value = response.body()
                } else {
                    Log.d(TAGS, "onFailure: ${response.message()}333")
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                is_loading.value = false
                Log.e(TAGS, "onFailure = ${t.message.toString()}" )
            }
        })

    }


}