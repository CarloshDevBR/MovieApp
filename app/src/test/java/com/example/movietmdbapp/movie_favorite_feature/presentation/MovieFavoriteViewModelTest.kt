package com.example.movietmdbapp.movie_favorite_feature.presentation

import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieFavoriteFactory
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieFavoriteViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val getMoviesFavoriteUseCase = mockk<GetMoviesFavoriteUseCase>()

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase)
    }

    private val fakePagingDataFavoriteMovie = listOf(
        MovieFavoriteFactory().create(poster = MovieFavoriteFactory.Poster.Avengers),
        MovieFavoriteFactory().create(poster = MovieFavoriteFactory.Poster.JohnWick)
    )

    @Test
    fun `must validate the data object values when calling list of favorites`() = runTest {
        // Given
        coEvery { getMoviesFavoriteUseCase.invoke() } returns flowOf(fakePagingDataFavoriteMovie)

        // When
        val result = viewModel.uiState.movies.first()

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(fakePagingDataFavoriteMovie[0])
    }
}