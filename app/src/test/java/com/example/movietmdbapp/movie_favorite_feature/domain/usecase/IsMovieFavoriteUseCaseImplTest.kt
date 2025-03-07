package com.example.movietmdbapp.movie_favorite_feature.domain.usecase

import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.util.ResultData
import com.example.movietmdbapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class IsMovieFavoriteUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val movieFavoriteRepository = mockk<MovieFavoriteRepository>()

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `must return Success from ResultStatus when the repository returns success with the value equal to true`() =
        runTest {
            // Given
            coEvery { movieFavoriteRepository.isFavorite(any()) } returns true

            // When
            val result = isMovieFavoriteUseCase.invoke(
                params = IsMovieFavoriteUseCase.Params(movieId = movie.id)
            ).first()

            // Then
            assertThat(result).isEqualTo(ResultData.Success(true))
        }

    @Test
    fun `should return Failure from ResultStatus when the repository throws an exception`() = runTest {
        // Given
        val exception = RuntimeException()

        coEvery { movieFavoriteRepository.isFavorite(movieId = movie.id) } throws exception

        // When
        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movieId = movie.id)
        ).first()

        // Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }
}