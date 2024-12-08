package com.example.movietmdbapp.core.util

import com.example.movietmdbapp.BuildConfig

fun String?.toPostUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackDropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"