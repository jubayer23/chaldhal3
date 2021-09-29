package com.android.chaldhal.data.repository

import PAGE_SIZE_LIMIT
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.android.chaldhal.data.api.ApiService
import com.android.chaldhal.data.models.ApiResponse
import com.android.chaldhal.data.models.Result
import com.android.chaldhal.data.pagingSource.UserListPagingSource
import javax.inject.Inject
import javax.inject.Singleton


interface CryptoListRepository {
    suspend fun getHomeList(): LiveData<PagingData<Result>>
}

@Singleton
class HomeRepositoryImpl @Inject constructor(private val service: ApiService, ) :
    CryptoListRepository {
    override suspend fun getHomeList(): LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserListPagingSource(service)
            }
        ).liveData
    }
}