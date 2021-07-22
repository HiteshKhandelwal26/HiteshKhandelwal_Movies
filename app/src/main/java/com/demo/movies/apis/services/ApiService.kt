package com.demo.movies.apis.services

import com.demo.movies.data.models.MovieList
import retrofit2.http.GET

/*Retrofit Service class - defines the API services to fetch the data*/
interface ApiService {
    // To get the list of movies
    @GET("/search/shows?q=super")
    suspend fun getMovieList(): MovieList
}
