package com.example.movietmdbapp.core.domain.model

class MovieFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> Movie(
            id = 1,
            title = "Avengers",
            voteAverage = 7.1,
            imageUrl = "Url"
        )
        Poster.JohnWick -> Movie(
            id = 2,
            title = "John Wick",
            voteAverage = 7.9,
            imageUrl = "Url"
        )
    }

    sealed class Poster {
        data object Avengers: Poster()

        data object JohnWick: Poster()
    }
}