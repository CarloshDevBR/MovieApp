package com.example.movietmdbapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.movietmdbapp.ui.theme.black
import com.example.movietmdbapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = white,
    backgroundColor: Color = black
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = title),
                color = textColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )
}