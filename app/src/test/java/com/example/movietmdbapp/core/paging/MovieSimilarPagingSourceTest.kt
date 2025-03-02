package com.example.movietmdbapp.core.paging

import androidx.paging.PagingSource
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieFactory
import com.example.movietmdbapp.core.domain.paging.MoviePagingFactory
import com.example.movietmdbapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieSimilarPagingSourceTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private var remoteDataSource = mockk<MovieDetailsRemoteDataSource>()

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val movieSimilarPagingSource by lazy {
        MovieSimilarPagingSource(
            movieId = 1,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `must return a success load result when load is called`() = runTest {
        // Given
        coEvery { remoteDataSource.getMoviesSimilar(any(), any()) } returns moviePagingFactory

        // When
        val result = movieSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            movieFactory.create(MovieFactory.Poster.Avengers),
            movieFactory.create(MovieFactory.Poster.JohnWick)
        )

        // Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(result)
    }

    @Test
    fun `must return a error load result when load is called`() = runTest {
        // Given
        val exception = RuntimeException()

        coEvery { remoteDataSource.getMoviesSimilar(any(), any()) } throws exception

        // When
        val result = movieSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        // Then
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}