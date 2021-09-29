package com.android.chaldhal.data.api


import com.android.chaldhal.data.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getHomeData(
        @Query("results") limit: Int?,
        @Query("seed") seed: String?,
        @Query("inc") inc: String?
    ): ApiResponse

}