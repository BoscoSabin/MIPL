package com.example.msiltask.data.rest

import com.example.msiltask.data.model.RepoResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("search/repositories?q=tetris+language:assembly")
    fun getPublicRepos(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int
    ): Call<RepoResponse>
}