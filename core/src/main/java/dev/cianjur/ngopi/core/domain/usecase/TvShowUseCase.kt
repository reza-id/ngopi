package dev.cianjur.ngopi.core.domain.usecase

import dev.cianjur.ngopi.core.data.Resource
import dev.cianjur.ngopi.core.domain.model.Nonton
import kotlinx.coroutines.flow.Flow

interface TvShowUseCase {

    fun getPopularTvShows(): Flow<Resource<List<Nonton>>>
    fun getFavoriteTvShows(): Flow<List<Nonton>>
    suspend fun setFavoriteTvShow(tvShow: Nonton, isFavorite: Boolean)
}
