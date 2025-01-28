package com.example.movietmdbapp.movie_popular_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.movietmdbapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    voteAvarege: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit
) {
    Box(modifier = modifier) {
        MovieRate(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 6.dp, bottom = 8.dp)
                .background(Color.Black),
            rate = voteAvarege
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(4.dp)
                .clickable { onClick(id) },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            AsyncImageUrl(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clip(RoundedCornerShape(8.dp)),
                imageUrl = imageUrl,
            )
        }
    }
}

@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(
        id = 2,
        voteAvarege = 6.2,
        imageUrl = "",
        onClick = {}
    )
}