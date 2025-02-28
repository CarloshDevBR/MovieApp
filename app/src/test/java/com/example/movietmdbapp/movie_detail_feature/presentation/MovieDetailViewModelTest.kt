package com.example.movietmdbapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieDetailFactory
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.util.ResultData
import com.example.movietmdbapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private val getMovieDetailsUseCase = mockk<GetMovieDetailsUseCase>()

    private val addMovieFavoriteUseCase = mockk<AddMovieFavoriteUseCase>()

    private val isMovieFavoriteUseCase = mockk<IsMovieFavoriteUseCase>()

    private val deleteMovieFavoriteUseCase = mockk<DeleteMovieFavoriteUseCase>()

    private val movieDetailsFactory =
        MovieDetailFactory().create(poster = MovieDetailFactory.Poster.Avengers)

    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(MovieFactory.Poster.Avengers),
            MovieFactory().create(MovieFactory.Poster.JohnWick)
        )
    )

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true).apply {
        every { get<Int>("movieId") } returns movie.id
    }

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            addMovieFavoriteUseCase = addMovieFavoriteUseCase,
            isMovieFavoriteUseCase = isMovieFavoriteUseCase,
            deleteMovieFavoriteUseCase = deleteMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `must notify uiState with Success when get movies similar and movie details returns success`() =
        runTest {
            // Given
            every { getMovieDetailsUseCase.invoke(any()) } returns flowOf(
                ResultData.Success(flowOf(pagingData) to movieDetailsFactory)
            )

            val slot = slot<GetMovieDetailsUseCase.Params>()

            // When
            viewModel.uiState.isLoading

            // Then
            verify { getMovieDetailsUseCase.invoke(capture(slot)) }

            val capturedValue = slot.captured

            assertThat(movieDetailsFactory.id).isEqualTo(capturedValue.movieId)

            val movieDetails = viewModel.uiState.movieDetails

            val results = viewModel.uiState.results

            assertThat(movieDetails).isNotNull()
            assertThat(results).isNotNull()
        }

    @Test
    fun `must notify uiState with Failure when get movies details and returns exceptions`() =
        runTest {
            // Given
            val exception = Exception("erro")

            every { getMovieDetailsUseCase.invoke(any()) } returns flowOf(
                ResultData.Failure(
                    exception
                )
            )

            // When
            viewModel.uiState.isLoading

            // Then
            val error = viewModel.uiState.error

            assertThat(exception.message).isEqualTo(error)
        }

    @Test
    fun `must add movie to favorite and update uiState correctly`() = runTest {
        // Given
        every { getMovieDetailsUseCase.invoke(any()) } returns flowOf(
            ResultData.Success(flowOf(pagingData) to movieDetailsFactory)
        )

        coEvery { addMovieFavoriteUseCase.invoke(any()) } returns flowOf(ResultData.Success(Unit))

        // When
        viewModel.onAddFavorite(movie)

        // Then
        coVerify { addMovieFavoriteUseCase.invoke(AddMovieFavoriteUseCase.Params(movie)) }

        assertThat(Color.Red).isEqualTo(viewModel.uiState.iconColor)
    }

    @Test
    fun `must remove the movie from favorites and update the uiState correctly`() = runTest {
        // Given
        every { getMovieDetailsUseCase.invoke(any()) } returns flowOf(
            ResultData.Success(flowOf(pagingData) to movieDetailsFactory)
        )

        coEvery { addMovieFavoriteUseCase.invoke(any()) } returns flowOf(ResultData.Success(Unit))

        coEvery { deleteMovieFavoriteUseCase.invoke(any()) } returns flowOf(ResultData.Success(Unit))

        coEvery { isMovieFavoriteUseCase.invoke(any()) } returns flowOf(ResultData.Success(true))

        // When
        viewModel.onAddFavorite(movie)

        // Then
        assertThat(Color.Red).isEqualTo(viewModel.uiState.iconColor)

        viewModel.onAddFavorite(movie)

        coVerify { deleteMovieFavoriteUseCase.invoke(DeleteMovieFavoriteUseCase.Params(movie)) }

        assertThat(Color.White).isEqualTo(viewModel.uiState.iconColor)
    }
}
