package com.example.movietmdbapp.movie_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieDetails
import com.example.movietmdbapp.core.util.Constants
import com.example.movietmdbapp.core.util.ResultData
import com.example.movietmdbapp.core.util.UtilFunctions
import com.example.movietmdbapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.example.movietmdbapp.movie_detail_feature.presentation.state.MovieDetailState
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(MovieDetailState())
        private set

    private val movieId =
        savedStateHandle.get<Int>(key = Constants.ARGUMENTS.MOVIE_DETAIL_ARGUMENT_KEY)

    init {
        movieId?.let { safeMovieId ->
            // checkedFavorite(MovieDetailEvent.CheckedFavorite(safeMovieId))

            getMovieDetail(MovieDetailEvent.GetMovieDetail(safeMovieId))
        }
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun onAddFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data?.second,
                                    results = resultData.data?.first ?: emptyFlow()
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = resultData.e?.message.toString()
                                )

                                UtilFunctions.logError(
                                    "DETAIL-ERROR",
                                    resultData.e?.message.toString()
                                )
                            }

                            ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                )
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.Red)
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao cadastrar filme")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = if (result.data == true) {
                                    uiState.copy(iconColor = Color.Red)
                                } else {
                                    uiState.copy(iconColor = Color.White)
                                }
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Um erro ocorreu")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(iconColor = Color.White)
                            }

                            is ResultData.Failure -> {
                                UtilFunctions.logError("DETAIL", "Erro ao favoritar")
                            }

                            ResultData.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}