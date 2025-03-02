package com.example.movietmdbapp.movie_detail_feature.data.source

import com.example.movietmdbapp.core.data.remote.MovieService
import com.example.movietmdbapp.core.domain.model.MovieDetails
import com.example.movietmdbapp.core.domain.model.MoviePaging
import com.example.movietmdbapp.core.paging.MovieSimilarPagingSource
import com.example.movietmdbapp.core.util.toBackDropUrl
import com.example.movietmdbapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.example.movietmdbapp.movie_popular_feature.data.mapper.toMovie
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)

        val genres = response.genres.map { it.name }

        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackDropUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging {
        val response = service.getMoviesSimilar(page = page, moveId = movieId)

        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(this, movieId = movieId)
    }
}