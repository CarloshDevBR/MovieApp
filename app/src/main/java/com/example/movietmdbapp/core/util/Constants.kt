package com.example.movietmdbapp.core.util

import com.example.movietmdbapp.BuildConfig

object Constants {
    const val API_KEY_HEADER = "Authorization"
    const val API_KEY_VALUE = "Bearer ${BuildConfig.API_KEY}"
    const val LANGUAGE_PARAM = "language"
    const val LANGUAGE_VALUE = "pt-BR"

    object ARGUMENTS {
        const val MOVIE_DETAIL_ARGUMENT_KEY = "movieId"
    }
}