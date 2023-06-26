package com.example.mygithubapps.network
import com.example.mygithubapps.response.DetailUserResponse
import com.example.mygithubapps.response.GithubResponse
import com.example.mygithubapps.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("/users/{username}/following")
    fun getFollowings(@Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String
    ): Call<GithubResponse>
}