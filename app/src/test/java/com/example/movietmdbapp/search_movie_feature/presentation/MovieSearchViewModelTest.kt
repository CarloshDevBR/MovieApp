package com.example.movietmdbapp.search_movie_feature.presentation

import androidx.paging.PagingData
import com.example.movietmdbapp.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import com.example.movietmdbapp.core.domain.model.MovieSearchFactory
import com.example.movietmdbapp.search_movie_feature.domain.usecases.GetMovieSearchUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieSearchViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private var getSearchMoviesUseCase = mockk<GetMovieSearchUseCase>()

    private val viewModel by lazy {
        MovieSearchViewModel(getSearchMoviesUseCase)
    }

    private val fakePagingDataSearchMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick)
        )
    )

    @Test
    fun `must validate paging data object values when calling movie search paging data`() = runTest {
        // Given
        every { getSearchMoviesUseCase.invoke(any()) } returns flowOf(fakePagingDataSearchMovies)

        // When
        viewModel.fetch("")

        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() = runTest {
        // Given
        every { getSearchMoviesUseCase.invoke(any()) } throws RuntimeException()

        // When
        viewModel.fetch()
    }
}