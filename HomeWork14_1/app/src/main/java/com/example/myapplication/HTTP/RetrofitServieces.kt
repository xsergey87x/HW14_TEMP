package com.example.myapplication.HTTP

import com.example.myapplication.Json.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServieces {
    @GET("3/discover/movie")
    /// fun getMovieList(): Call<MutableList<Movie>>
    fun getMovies(@Query("sort_by") sortBy: String, @Query("api_key") apiKey: String, @Query("primary_release_year") primaryReleaseYear: String, @Query("language") language: String): Call<Movie>
}




