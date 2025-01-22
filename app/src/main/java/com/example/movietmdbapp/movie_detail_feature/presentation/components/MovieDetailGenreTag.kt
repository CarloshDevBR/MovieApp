package com.example.movietmdbapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movietmdbapp.ui.theme.white

@Composable
fun MovieDetailGenreTag(genre: String) {
    Box(
        modifier = Modifier
            .border(width = 1.dp, color = white, shape = RoundedCornerShape(100.dp))
            .padding(10.dp)
    ) {
        Text(
            text = genre,
            color = white,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun GenreTagPreview() {
    MovieDetailGenreTag(genre = "Aventura")
}