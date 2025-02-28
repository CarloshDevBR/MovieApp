package com.example.movietmdbapp.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

class MovieDetailFactory {
    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> MovieDetails(
            id = 1,
            title = "Avengers",
            voteAverage = 7.1,
            overview = LoremIpsum(100).values.first(),
            backdropPathUrl = "Url",
            releaseDate = "04/05/2012",
            duration = 143,
            voteCount = 7,
            genres = listOf("Aventura", "Ação", "Ficção Científica")
        )
        Poster.JohnWick -> MovieDetails(
            id = 2,
            title = "John Wick",
            voteAverage = 7.9,
            overview = LoremIpsum(200).values.first(),
            backdropPathUrl = "Url",
            releaseDate = "02/07/2014",
            duration = 169,
            voteCount = 7,
            genres = listOf("Ação", "Thriller", "Crime")
        )
    }

    sealed class Poster {
        data object Avengers: Poster()

        data object JohnWick: Poster()
    }
}