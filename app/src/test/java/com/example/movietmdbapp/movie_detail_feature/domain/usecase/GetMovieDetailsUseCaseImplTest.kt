package com.example.movietmdbapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.MovieDetailFactory
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.domain.model.PagingSourceMoviesFactory
import com.example.movietmdbapp.core.util.ResultData
import com.example.movietmdbapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseImplTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private var movieDetailsRepository = mockk<MovieDetailsRepository>()

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailFactory =
        MovieDetailFactory().create(poster = MovieDetailFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(listOf(movieFactory))

    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCaseImpl(movieDetailsRepository)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests return success`() =
        runTest {
            // Given
            coEvery { movieDetailsRepository.getMovieDetails(movieId = movieFactory.id) } returns movieDetailFactory

            coEvery { movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id) } returns pagingSourceFake

            // When
            val result = getMovieDetailsUseCase.invoke(
                GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            // Then
            coVerify { movieDetailsRepository.getMovieDetails(movieId = movieFactory.id) }

            coVerify { movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id) }

            assertThat(result is ResultData.Success).isNotNull()
            assertThat(result is ResultData.Success).isTrue()
        }

    @Test
    fun `should return Error from ResultStatus when get movieDetails request returns error`() =
        runTest {
            // Given
            val exception = RuntimeException()

            every { movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id) } returns pagingSourceFake

            coEvery { movieDetailsRepository.getMovieDetails(movieId = movieFactory.id) } throws exception

            // When
            val result = getMovieDetailsUseCase.invoke(
                GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            // Then
            coVerify { movieDetailsRepository.getMovieDetails(movieId = movieFactory.id) }

            assertThat(result is ResultData.Failure).isTrue()
        }

    @Test
    fun `should return a ResultStatus error when getting similar movies returns an error`() =
        runTest {
            // Given
            val exception = RuntimeException()

            every { movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id) } throws exception

            coEvery { movieDetailsRepository.getMovieDetails(movieId = movieFactory.id) } returns movieDetailFactory

            // When
            val result = getMovieDetailsUseCase.invoke(
                GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            // Then
            verify { movieDetailsRepository.getMoviesSimilar(movieId = movieFactory.id) }

            assertThat(result is ResultData.Failure).isTrue()
        }
}
