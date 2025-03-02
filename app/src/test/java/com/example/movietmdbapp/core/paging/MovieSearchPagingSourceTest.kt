package com.example.movietmdbapp.core.paging

import androidx.paging.PagingSource
import com.example.movietmdbapp.TestDispatcherRule
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.core.domain.model.MovieSearchFactory
import com.example.movietmdbapp.core.domain.paging.MovieSearchPagingFactory
import com.example.movietmdbapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieSearchPagingSourceTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private var remoteDataSource = mockk<MovieSearchRemoteDataSource>()

    private val movieSearchFactory = MovieSearchFactory()

    private val movieSearchPagingFactory = MovieSearchPagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `must return a success load result when load is called`() = runTest {
        // Given
        coEvery { remoteDataSource.getSearchMovies(any(), any()) } returns movieSearchPagingFactory

        // When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            movieSearchFactory.create(MovieSearchFactory.Poster.Avengers),
            movieSearchFactory.create(MovieSearchFactory.Poster.JohnWick)
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

        coEvery { remoteDataSource.getSearchMovies(any(), any()) } throws exception

        // When
        val result = movieSearchPagingSource.load(
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