package com.athar.suitmediatestathar

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getStories(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ) : Response<UserResponse>

}
