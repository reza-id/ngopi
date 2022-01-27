package dev.cianjur.ngopi.core.data.source.local.room

import androidx.room.*
import dev.cianjur.ngopi.core.data.source.local.entity.MovieEntity
import dev.cianjur.ngopi.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NontonDao {

    @Query("SELECT * FROM movies ORDER BY popularityIndex ASC")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    suspend fun updateFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_shows ORDER BY popularityIndex ASC")
    fun getPopularTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE isFavorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tv_shows: List<TvShowEntity>)

    @Update
    suspend fun updateFavoriteTvShow(movie: TvShowEntity)
}
