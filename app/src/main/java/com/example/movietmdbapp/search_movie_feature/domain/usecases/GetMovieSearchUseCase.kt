package com.example.movietmdbapp.search_movie_feature.domain.usecases

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movietmdbapp.core.domain.model.MovieSearch
import com.example.movietmdbapp.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieSearchUseCase {
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>>

    data class Params(val query: String)
}

class GetMovieSearchUseCaseImpl @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUseCase {
    override fun invoke(params: GetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> {
        return repository.getSearchMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            )
        )
    }
}