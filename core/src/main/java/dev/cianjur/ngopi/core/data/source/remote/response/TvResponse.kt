package dev.cianjur.ngopi.core.data.source.remote.response

import androidx.annotation.Keep

@Keep
data class TvResponse(
    val backdrop_path: String,
    val first_air_date: String,
    val id: Int,
    val name: String,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
)
