package com.example.msiltask.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.msiltask.data.model.RepoResponse
import com.example.msiltask.data.rest.ApiBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepo {
    private val TAG = "HomeRepo"

    fun getPublicRepo(currentPage: Int): MutableLiveData<Result<RepoResponse>> {
        val liveData = MutableLiveData<Result<RepoResponse>>()
        ApiBuilder.getApiInterface().getPublicRepos(currentPage, 10)
            .enqueue(object :
                Callback<RepoResponse> {
                override fun onResponse(
                    call: Call<RepoResponse>,
                    response: Response<RepoResponse>
                ) {
                    Log.d(TAG, "apiCallBack onResponse  ${response.body()}")
                    if (response.isSuccessful && response.body() != null) {
                        liveData.value = Result.success(response.body()!!)
                    } else liveData.value = Result.failure(Throwable(response.message()))
                    call.cancel()
                }

                override fun onFailure(call: Call<RepoResponse>, t: Throwable) {
                    Log.d(TAG, "apiCallBack onResponse  ${t.message}")
                    liveData.value = Result.failure(t)
                }
            }
            )
        return liveData
    }

}