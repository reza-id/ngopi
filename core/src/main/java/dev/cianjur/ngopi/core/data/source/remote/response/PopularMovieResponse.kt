package dev.cianjur.ngopi.core.data.source.remote.response

import androidx.annotation.Keep

@Keep
data class PopularMovieResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)
