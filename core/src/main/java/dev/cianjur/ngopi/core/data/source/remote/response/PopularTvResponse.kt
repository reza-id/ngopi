package dev.cianjur.ngopi.core.data.source.remote.response

import androidx.annotation.Keep

@Keep
data class PopularTvResponse(
    val page: Int,
    val results: List<TvResponse>,
    val total_pages: Int,
    val total_results: Int
)
