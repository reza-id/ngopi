package dev.cianjur.ngopi.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val popularity: Double,
    val popularityIndex: Int,
    val vote_count: Int,
    val vote_average: Double,
    val release_date: String,
    val overview: String,
    val isFavorite: Boolean,

    @PrimaryKey(autoGenerate = true)
    val movieId: Int = 0,
)
