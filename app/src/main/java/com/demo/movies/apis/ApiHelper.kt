package com.demo.movies.apis

import com.demo.movies.apis.services.ApiService
/* API Helper class to help with the ApiService call*/
class ApiHelper(private val apiService: ApiService) {

    suspend fun getMoviesList() = apiService.getMovieList()
}
