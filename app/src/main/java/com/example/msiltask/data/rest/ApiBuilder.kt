package com.example.msiltask.data.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiBuilder {
    private const val SERVER_URL = "https://api.github.com/"
    fun getApiInterface(): ApiInterface {
        // change your base URL
        val httpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Accept", "application/x-www-form-urlencoded")
                    .build()
                chain.proceed(newRequest)
            }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}