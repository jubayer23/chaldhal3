package com.android.chaldhal.data.pagingSource

import PAGE_SIZE_LIMIT
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.chaldhal.data.models.Result
import com.android.chaldhal.data.api.ApiService


class UserListPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        return try {
            val nextPage = params.key ?: 0
            val results = nextPage.times(PAGE_SIZE_LIMIT)
            val seed = "chalkboard"
            val inc = "name,dob"
            val response = apiService.getHomeData(results,seed,inc)


            LoadResult.Page(
                data = response.results as List<Result>,
                prevKey = null,
                nextKey = if (response.results?.isEmpty() == true) null else nextPage + 1,
                itemsBefore = 0,
                itemsAfter = 0
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return 0
    }

}