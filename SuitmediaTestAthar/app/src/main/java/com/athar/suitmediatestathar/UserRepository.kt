package com.athar.suitmediatestathar

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class UserRepository(private val apiService: ApiService) {
    fun getStory(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}