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
class AddMovieFavoriteUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val movieFavoriteRepository = mockk<MovieFavoriteRepository>()

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.JohnWick)

    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns success equal to unit`() =
        runTest {
            // Given
            coEvery { movieFavoriteRepository.insert(movie) } returns Unit

            // When
            val result = addMovieFavoriteUseCase.invoke(
                params = AddMovieFavoriteUseCase.Params(
                    movie = movie
                )
            ).first()

            // Then
            assertThat(result).isEqualTo(ResultData.Success(Unit))
        }

    @Test
    fun `should return Failure from ResultStatus when the repository throws an exception`() =
        runTest {
            // Given
            val exception = RuntimeException()

            coEvery { movieFavoriteRepository.insert(movie) } throws exception

            // When
            val result = addMovieFavoriteUseCase.invoke(
                params = AddMovieFavoriteUseCase.Params(movie = movie)
            ).first()

            // Then
            assertThat(result).isEqualTo(ResultData.Failure(exception))
        }
}