package com.example.movietmdbapp.movie_favorite_feature.domain.usecase

import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.util.ResultData
import com.example.movietmdbapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>

    data class Params(val movie: Movie)
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : DeleteMovieFavoriteUseCase {
    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val delete = repository.delete(params.movie)

            emit(ResultData.Success(delete))
        }.flowOn(Dispatchers.IO)
    }
}