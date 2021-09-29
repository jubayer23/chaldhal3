package com.android.chaldhal.ui.userlist

import PAGE_SIZE_LIMIT
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.chaldhal.data.pagingSource.UserListPagingSource
import com.android.chaldhal.data.models.ApiResponse
import com.android.chaldhal.data.models.Result
import com.android.chaldhal.data.api.ApiService
import com.android.chaldhal.data.repository.HomeRepositoryImpl
import com.android.chaldhal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiService: ApiService,private val repositoryImpl: HomeRepositoryImpl) : ViewModel() {



    private val _homeList = MutableLiveData<PagingData<Result>>()

    suspend fun getHomeList(): LiveData<PagingData<Result>> {
        val response = repositoryImpl.getHomeList().cachedIn(viewModelScope)
        _homeList.value = response.value
        return response
    }


    fun homeDataRequest(): Flow<PagingData<Result>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE_LIMIT)) {
            UserListPagingSource(apiService)
        }.flow

}