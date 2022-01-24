package dev.cianjur.ngopi.core.data.source.local

import dev.cianjur.ngopi.core.data.source.local.entity.MovieEntity
import dev.cianjur.ngopi.core.data.source.local.entity.TvShowEntity
import dev.cianjur.ngopi.core.data.source.local.room.NontonDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val nontonDao: NontonDao) {

    fun getPopularMovies(): Flow<List<MovieEntity>> = nontonDao.getPopularMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = nontonDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = nontonDao.insertMovies(movies)

    suspend fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        nontonDao.updateFavoriteMovie(movie.copy(isFavorite = isFavorite))
    }

    fun getPopularTvShows(): Flow<List<TvShowEntity>> = nontonDao.getPopularTvShows()

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> = nontonDao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = nontonDao.insertTvShows(tvShows)

    suspend fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        nontonDao.updateFavoriteTvShow(tvShow.copy(isFavorite = isFavorite))
    }
}
