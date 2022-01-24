package dev.cianjur.ngopi.core.data

import dev.cianjur.ngopi.core.data.source.local.entity.MovieEntity
import dev.cianjur.ngopi.core.data.source.local.entity.TvShowEntity
import dev.cianjur.ngopi.core.data.source.remote.response.MovieResponse
import dev.cianjur.ngopi.core.data.source.remote.response.TvResponse
import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.core.domain.model.NontonType

object DataMapper {
    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> =
        input.map {
            MovieEntity(
                movieId = it.id,
                title = it.title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                popularity = it.popularity,
                vote_count = it.vote_count,
                vote_average = it.vote_average,
                release_date = it.release_date,
                overview = it.overview,
                isFavorite = false,
            )
        }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Nonton> =
        input.map {
            Nonton(
                itemId = it.movieId,
                title = it.title,
                posterPath = it.poster_path ?: "",
                backdropPath = it.backdrop_path ?: "",
                popularity = it.popularity,
                voteCount = it.vote_count,
                voteAverage = it.vote_average,
                releaseDate = it.release_date,
                overview = it.overview,
                isFavorite = it.isFavorite,
                itemType = NontonType.MOVIE,
            )
        }

    fun mapMovieDomainToEntity(input: Nonton) = MovieEntity(
        movieId = input.itemId,
        title = input.title,
        poster_path = input.posterPath,
        backdrop_path = input.backdropPath,
        popularity = input.popularity,
        vote_count = input.voteCount,
        vote_average = input.voteAverage,
        release_date = input.releaseDate,
        overview = input.overview,
        isFavorite = input.isFavorite,
    )

    fun mapTvShowResponsesToEntities(input: List<TvResponse>): List<TvShowEntity> =
        input.map {
            TvShowEntity(
                tvId = it.id,
                name = it.name,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                popularity = it.popularity,
                vote_count = it.vote_count,
                vote_average = it.vote_average,
                first_air_date = it.first_air_date,
                overview = it.overview,
                isFavorite = false,
            )
        }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<Nonton> =
        input.map {
            Nonton(
                itemId = it.tvId,
                title = it.name,
                posterPath = it.poster_path ?: "",
                backdropPath = it.backdrop_path ?: "",
                popularity = it.popularity,
                voteCount = it.vote_count,
                voteAverage = it.vote_average,
                releaseDate = it.first_air_date,
                overview = it.overview,
                isFavorite = it.isFavorite,
                itemType = NontonType.TV_SHOW,
            )
        }

    fun mapTvShowDomainToEntity(input: Nonton) = TvShowEntity(
        tvId = input.itemId,
        name = input.title,
        poster_path = input.posterPath,
        backdrop_path = input.backdropPath,
        popularity = input.popularity,
        vote_count = input.voteCount,
        vote_average = input.voteAverage,
        first_air_date = input.releaseDate,
        overview = input.overview,
        isFavorite = input.isFavorite,
    )
}
