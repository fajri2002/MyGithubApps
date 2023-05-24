package com.example.mygithubapps
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String,
        @Header("Authorization") token: String = "token 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"
    ): Call<DetailUserResponse>

    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String,
        @Header("Authorization") token: String = "token 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"
    ): Call<List<ItemsItem>>

    @GET("/users/{username}/following")
    fun getFollowing(@Path("username") username: String,
                     @Header("Authorization") token: String = "token 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"
    ): Call<List<ItemsItem>>

    @GET("search/users")
    fun searchUser(
        @Query("q") query: String,
        @Header("Authorization") token: String = "token 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"
    ): Call<GithubResponse>
}