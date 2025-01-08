package com.example.movietmdbapp.search_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.search_movie_feature.domain.repository.MovieSearchRepository
import com.example.movietmdbapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieSearchRepositoryImpl constructor(
    private val remoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {
    override fun getSearchMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { remoteDataSource.getSearchMoviePagingSource(query = query) }
        ).flow
    }
}