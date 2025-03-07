package com.example.movietmdbapp.movie_favorite_feature.domain.usecase

import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesFavoriteUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val movieFavoriteRepository = mockk<MovieFavoriteRepository>()

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    private val getMoviesFavoriteUseCase by lazy {
        GetMoviesFavoriteUseCaseImpl(movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns a list of movies`() =
        runTest {
            // Given
            every { movieFavoriteRepository.getMovies() } returns flowOf(movies)

            // When
            val result = getMoviesFavoriteUseCase.invoke().first()

            // Then
            assertThat(result).isNotEmpty()
            assertThat(result).contains(movies[1])
        }

    @Test
    fun `should emit an empty stream when exception is thrown when calling the invoke method`() = runTest {
        // Given
        val exception = RuntimeException()

        every { movieFavoriteRepository.getMovies() } throws exception

        // When
        val result = getMoviesFavoriteUseCase.invoke().toList()

        // Then
        verify { movieFavoriteRepository.getMovies() }

        assertThat(result).isEmpty()
    }
}