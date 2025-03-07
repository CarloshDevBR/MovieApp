package com.example.movietmdbapp.movie_popular_feature.presentation

import androidx.paging.PagingData
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviePopularViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val getPopularMoviesUseCase = mockk<GetPopularMoviesUseCase>()

    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling paging data from movies`() = runTest {
        // Given
        every { getPopularMoviesUseCase.invoke(any()) } returns flowOf(fakePagingDataMovies)

        // When
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() = runTest {
            // Given
            every { getPopularMoviesUseCase.invoke(any()) } throws RuntimeException()

            // When
            val result = viewModel.uiState.movies.first()

            // Then
            assertThat(result).isNull()
    }
}