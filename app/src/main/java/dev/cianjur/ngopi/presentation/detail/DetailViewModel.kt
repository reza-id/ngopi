package dev.cianjur.ngopi.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.core.domain.model.NontonType
import dev.cianjur.ngopi.core.domain.usecase.MovieUseCase
import dev.cianjur.ngopi.core.domain.usecase.TvShowUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase,
) : ViewModel() {

    fun setFavorite(nonton: Nonton, newStatus: Boolean) = viewModelScope.launch {
        if (nonton.itemType == NontonType.MOVIE) {
            movieUseCase.setFavoriteMovie(nonton, newStatus)
        } else {
            tvShowUseCase.setFavoriteTvShow(nonton, newStatus)
        }
    }
}
