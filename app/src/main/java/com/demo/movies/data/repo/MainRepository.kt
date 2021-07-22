package com.demo.movies.data.repo

import com.demo.movies.apis.ApiHelper
/*Since I've used a Repository pattern, therefore linking the ApiHelper class by using a Repository class*/
class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovieList() = apiHelper.getMoviesList()
}
