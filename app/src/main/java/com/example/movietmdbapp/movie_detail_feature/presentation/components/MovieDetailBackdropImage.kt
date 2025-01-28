package com.example.movietmdbapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movietmdbapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(backdropImageUrl: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier) {
        AsyncImageUrl(
            imageUrl = backdropImageUrl,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp)
    )
}