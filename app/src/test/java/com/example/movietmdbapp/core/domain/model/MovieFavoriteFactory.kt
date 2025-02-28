package com.example.movietmdbapp.core.domain.model

class MovieFavoriteFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> Movie(
            id = 1,
            voteAverage = 7.1,
            title = "Avengers",
            imageUrl = "Url"
        )

        Poster.JohnWick -> Movie(
            id = 2,
            voteAverage = 7.9,
            title = "JohnWick",
            imageUrl = "Url"
        )
    }

    sealed class Poster {
        data object Avengers : Poster()

        data object JohnWick : Poster()
    }
}