package com.example.movietmdbapp.movie_favorite_feature.di

import com.example.movietmdbapp.core.data.local.dao.MovieDao
import com.example.movietmdbapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import com.example.movietmdbapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import com.example.movietmdbapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import com.example.movietmdbapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import com.example.movietmdbapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {
    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao = dao)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteRepository(movieFavoriteLocalDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(localDataSource = movieFavoriteLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(repository = repository)
    }

    @Provides
    @Singleton
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetMoviesFavoriteUseCase(repository: MovieFavoriteRepository): GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(repository = repository)
    }

    @Provides
    @Singleton
    fun provideIsMovieFavoriteUseCase(repository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(repository = repository)
    }
}