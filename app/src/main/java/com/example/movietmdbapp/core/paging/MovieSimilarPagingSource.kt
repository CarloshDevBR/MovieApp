package com.example.movietmdbapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movietmdbapp.core.domain.model.Movie
import com.example.movietmdbapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1

            val response = remoteDataSource.getMoviesSimilar(page = pageNumber, movieId = movieId)

            val movies = response.movies

            val totalPages = response.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1
            )
        } catch (exception: Exception) {
            exception.printStackTrace()

            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}