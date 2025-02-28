package com.example.movietmdbapp.core.domain.model

class MovieSearchFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> MovieSearch(
            id = 1,
            voteAverage = 7.1,
            imageUrl = "Url"
        )

        Poster.JohnWick -> MovieSearch(
            id = 2,
            voteAverage = 7.9,
            imageUrl = "Url",
        )
    }

    sealed class Poster {
        data object Avengers : Poster()

        data object JohnWick : Poster()
    }
}