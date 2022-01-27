package dev.cianjur.ngopi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nonton(
    val itemId: Int,
    val itemType: NontonType,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val popularityIndex: Int,
    val voteCount: Int,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String,
    val isFavorite: Boolean,
) : Parcelable
