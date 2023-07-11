package com.athar.suitmediatestathar

import android.content.Context

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}