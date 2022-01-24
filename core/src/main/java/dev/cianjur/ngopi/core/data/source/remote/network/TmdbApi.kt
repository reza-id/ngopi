package dev.cianjur.ngopi.core.data.source.remote.network

import dev.cianjur.ngopi.core.data.source.remote.response.PopularMovieResponse
import dev.cianjur.ngopi.core.data.source.remote.response.PopularTvResponse
import retrofit2.http.GET

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): PopularMovieResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(): PopularTvResponse
}
