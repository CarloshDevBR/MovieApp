package com.example.movietmdbapp.search_movie_feature.domain.usecases

import androidx.paging.PagingConfig
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.domain.model.MovieSearchFactory
import com.example.movietmdbapp.core.domain.model.PagingSourceMoviesFactory
import com.example.movietmdbapp.core.domain.model.PagingSourceMoviesSearchFactory
import com.example.movietmdbapp.movie_popular_feature.domain.repository.MoviePopularRepository
import com.example.movietmdbapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.example.movietmdbapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import com.example.movietmdbapp.search_movie_feature.domain.repository.MovieSearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieSearchUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val movieSearchRepository = mockk<MovieSearchRepository>()

    private val movie = MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesSearchFactory().create(listOf(movie))

    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImpl(movieSearchRepository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            // Given
            every { movieSearchRepository.getSearchMovies(query = "") } returns pagingSourceFake

            // When
            val result = getMovieSearchUseCase.invoke(
                params = GetMovieSearchUseCase.Params(
                    query = "",
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            ).first()

            // Then
            verify { movieSearchRepository.getSearchMovies(query = "") }

            assertThat(result).isNotNull()
        }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            // Given
            val exception = RuntimeException()

            every { movieSearchRepository.getSearchMovies(query = "") } throws exception

            // When
            val result = getMovieSearchUseCase.invoke(
                params = GetMovieSearchUseCase.Params(
                    query = "",
                    PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            val resultList = result.toList()

            // Then
            verify { movieSearchRepository.getSearchMovies(query = "") }

            assertThat(resultList).isEmpty()
        }
}