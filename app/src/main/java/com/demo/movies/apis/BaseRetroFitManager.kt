package com.demo.movies.apis

import com.demo.movies.apis.services.ApiService
import com.demo.movies.application.NestConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*Setting up the Network Layer - Retrofit Builder class*/
object BaseRetroFitManager {

    private const val URL = NestConfig.ENDPOINT

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API_SERVICE: ApiService = getRetrofit().create(ApiService::class.java)
}
