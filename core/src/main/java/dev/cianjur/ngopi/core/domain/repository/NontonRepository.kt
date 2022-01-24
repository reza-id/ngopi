package dev.cianjur.ngopi.core.domain.repository

import dev.cianjur.ngopi.core.data.Resource
import dev.cianjur.ngopi.core.domain.model.Nonton
import kotlinx.coroutines.flow.Flow

interface NontonRepository {

    fun getPopularMovies(): Flow<Resource<List<Nonton>>>

    fun getFavoriteMovies(): Flow<List<Nonton>>

    suspend fun setFavoriteMovie(movie: Nonton, isFavorite: Boolean)

    fun getPopularTvShows(): Flow<Resource<List<Nonton>>>

    fun getFavoriteTvShows(): Flow<List<Nonton>>

    suspend fun setFavoriteTvShow(tvShow: Nonton, isFavorite: Boolean)
}
