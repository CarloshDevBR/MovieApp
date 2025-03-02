package com.example.movietmdbapp.movie_popular_feature.data.source

import com.example.movietmdbapp.core.data.remote.MovieService
import com.example.movietmdbapp.core.domain.model.MoviePaging
import com.example.movietmdbapp.core.paging.MoviePagingSource
import com.example.movietmdbapp.movie_popular_feature.data.mapper.toMovie
import com.example.movietmdbapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {
    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MoviePaging {
        val response = service.getPopularMovies(page = page)

        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )
    }
}