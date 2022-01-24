package dev.cianjur.ngopi.core.domain.usecase

import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.core.domain.repository.NontonRepository

class MovieInteractor(private val repository: NontonRepository) : MovieUseCase {

    override fun getPopularMovies() = repository.getPopularMovies()

    override fun getFavoriteMovies() = repository.getFavoriteMovies()

    override suspend fun setFavoriteMovie(movie: Nonton, isFavorite: Boolean) = repository.setFavoriteMovie(movie, isFavorite)
}
