package dev.cianjur.ngopi.core.domain.usecase

import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.core.domain.repository.NontonRepository

class TvShowInteractor(private val repository: NontonRepository) : TvShowUseCase {

    override fun getPopularTvShows() = repository.getPopularTvShows()

    override fun getFavoriteTvShows() = repository.getFavoriteTvShows()

    override suspend fun setFavoriteTvShow(tvShow: Nonton, isFavorite: Boolean) = repository.setFavoriteTvShow(tvShow, isFavorite)
}
