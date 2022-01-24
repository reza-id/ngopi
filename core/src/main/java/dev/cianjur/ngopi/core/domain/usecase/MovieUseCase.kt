package dev.cianjur.ngopi.core.domain.usecase

import dev.cianjur.ngopi.core.data.Resource
import dev.cianjur.ngopi.core.domain.model.Nonton
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovies(): Flow<Resource<List<Nonton>>>
    fun getFavoriteMovies(): Flow<List<Nonton>>
    suspend fun setFavoriteMovie(movie: Nonton, isFavorite: Boolean)
}
