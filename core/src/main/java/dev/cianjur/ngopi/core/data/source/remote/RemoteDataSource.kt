package dev.cianjur.ngopi.core.data.source.remote

import dev.cianjur.ngopi.core.data.source.remote.network.ApiResponse
import dev.cianjur.ngopi.core.data.source.remote.network.TmdbApi
import dev.cianjur.ngopi.core.data.source.remote.response.MovieResponse
import dev.cianjur.ngopi.core.data.source.remote.response.TvResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class RemoteDataSource(private val tmdbApi: TmdbApi) {

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = tmdbApi.getPopularMovies()
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Timber.e(e)
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularTvShows(): Flow<ApiResponse<List<TvResponse>>> {
        return flow {
            try {
                val response = tmdbApi.getPopularTvShows()
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Timber.e(e)
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
