package com.athar.suitmediatestathar

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState


class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {

    companion object  {
        const val INITIAL_PAGE_INDEX = 1
        fun snapshot(items: List<DataItem>): PagingData<DataItem> {
            return PagingData.from(items)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getStories(position, params.loadSize)
            LoadResult.Page(
                data = responseData.body()?.data ?: emptyList(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.body()?.data.isNullOrEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}