package com.example.movietmdbapp.core.data.remote

import com.example.movietmdbapp.core.data.remote.response.MovieDetailResponse
import com.example.movietmdbapp.core.data.remote.response.MovieResponse
import com.example.movietmdbapp.core.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/movie")
    suspend fun serachMovie(
        @Query("page") page: Int,
        @Query("query") query: String
    ): SearchResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") moveId: Int
    ): MovieDetailResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getMoviesSimilar(
        @Path("movie_id") moveId: Int,
        @Query("page") page: Int
    ): MovieResponse
}