package dev.cianjur.ngopi.core.data

import dev.cianjur.ngopi.core.data.source.local.LocalDataSource
import dev.cianjur.ngopi.core.data.source.remote.RemoteDataSource
import dev.cianjur.ngopi.core.data.source.remote.response.MovieResponse
import dev.cianjur.ngopi.core.data.source.remote.response.TvResponse
import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.core.domain.repository.NontonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NontonRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : NontonRepository {

    override fun getPopularMovies(): Flow<Resource<List<Nonton>>> =
        object : NetworkBoundResource<List<Nonton>, List<MovieResponse>>() {
            override fun shouldFetch(data: List<Nonton>?) = data.isNullOrEmpty()
            override suspend fun createCall() = remoteDataSource.getPopularMovies()
            override suspend fun saveCallResult(data: List<MovieResponse>) =
                localDataSource.insertMovies(DataMapper.mapMovieResponsesToEntities(data))

            override fun loadFromDB() = localDataSource.getPopularMovies()
                .map { DataMapper.mapMovieEntitiesToDomain(it) }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Nonton>> =
        localDataSource.getFavoriteMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

    override suspend fun setFavoriteMovie(movie: Nonton, isFavorite: Boolean) =
        localDataSource.setFavoriteMovie(DataMapper.mapMovieDomainToEntity(movie), isFavorite)

    override fun getPopularTvShows(): Flow<Resource<List<Nonton>>> =
        object : NetworkBoundResource<List<Nonton>, List<TvResponse>>() {
            override fun shouldFetch(data: List<Nonton>?) = data.isNullOrEmpty()
            override suspend fun createCall() = remoteDataSource.getPopularTvShows()
            override suspend fun saveCallResult(data: List<TvResponse>) =
                localDataSource.insertTvShows(DataMapper.mapTvShowResponsesToEntities(data))

            override fun loadFromDB() = localDataSource.getPopularTvShows()
                .map { DataMapper.mapTvShowEntitiesToDomain(it) }
        }.asFlow()

    override fun getFavoriteTvShows(): Flow<List<Nonton>> =
        localDataSource.getFavoriteTvShows().map { DataMapper.mapTvShowEntitiesToDomain(it) }

    override suspend fun setFavoriteTvShow(tvShow: Nonton, isFavorite: Boolean) =
        localDataSource.setFavoriteTvShow(DataMapper.mapTvShowDomainToEntity(tvShow), isFavorite)
}
