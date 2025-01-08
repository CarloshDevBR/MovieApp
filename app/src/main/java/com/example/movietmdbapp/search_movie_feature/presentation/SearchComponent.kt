package com.example.movietmdbapp.search_movie_feature.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movietmdbapp.R
import com.example.movietmdbapp.search_movie_feature.presentation.components.MovieSearchEvent
import com.example.movietmdbapp.ui.theme.white

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (MovieSearchEvent) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it))
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query)
                }
            ) {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            }
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_movies))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = white,
            unfocusedTextColor = white,
            focusedPlaceholderColor = white,
            unfocusedPlaceholderColor = white,
            focusedTrailingIconColor = white,
            unfocusedTrailingIconColor = white
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp)
    )
}

@Preview
@Composable
private fun SearchComponentPreview() {
    SearchComponent(
        query = "",
        onSearch = {},
        onQueryChangeEvent = {}
    )
}