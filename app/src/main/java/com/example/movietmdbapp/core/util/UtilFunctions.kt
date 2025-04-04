package com.example.movietmdbapp.core.util

import timber.log.Timber

object UtilFunctions {
    fun logError(tag: String, message: String) {
        Timber.tag(tag).e("Error -> $message")
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).e("Info -> $message")
    }
}