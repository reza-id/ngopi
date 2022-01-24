package dev.cianjur.ngopi.presentation.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.cianjur.ngopi.core.domain.usecase.TvShowUseCase

class TvShowsViewModel(tvShowUseCase: TvShowUseCase) : ViewModel() {
    val tvShows = tvShowUseCase.getPopularTvShows().asLiveData()
}
