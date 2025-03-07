package com.example.movietmdbapp.movie_popular_feature.domain.usecase

import androidx.paging.PagingConfig
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.domain.model.PagingSourceMoviesFactory
import com.example.movietmdbapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMoviesUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val moviePopularRepository = mockk<MoviePopularRepository>()

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(listOf(movie))

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(moviePopularRepository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            // Given
            every { moviePopularRepository.getPopularMovies() } returns pagingSourceFake

            // When
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            ).first()

            // Then
            verify { moviePopularRepository.getPopularMovies() }

            assertThat(result).isNotNull()
        }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            // Given
            val exception = RuntimeException()

            every { moviePopularRepository.getPopularMovies() } throws exception

            // When
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            val resultList = result.toList()

            // Then
            verify { moviePopularRepository.getPopularMovies() }

            assertThat(resultList).isEmpty()
        }
}