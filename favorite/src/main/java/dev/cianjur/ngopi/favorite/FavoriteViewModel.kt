package dev.cianjur.ngopi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.cianjur.ngopi.core.domain.usecase.MovieUseCase
import dev.cianjur.ngopi.core.domain.usecase.TvShowUseCase

class FavoriteViewModel(
    movieUseCase: MovieUseCase,
    tvShowUseCase: TvShowUseCase,
) : ViewModel() {

    val movies = movieUseCase.getFavoriteMovies().asLiveData()
    val tvShows = tvShowUseCase.getFavoriteTvShows().asLiveData()
}
