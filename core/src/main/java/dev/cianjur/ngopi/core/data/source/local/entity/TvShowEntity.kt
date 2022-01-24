package dev.cianjur.ngopi.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TvShowEntity(
    val name: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val popularity: Double,
    val vote_count: Int,
    val vote_average: Double,
    val first_air_date: String,
    val overview: String,
    val isFavorite: Boolean,

    @PrimaryKey
    @NonNull
    val tvId: Int = 0,
)
