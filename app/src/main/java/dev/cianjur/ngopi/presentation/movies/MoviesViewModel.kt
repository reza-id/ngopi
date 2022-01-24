package dev.cianjur.ngopi.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.cianjur.ngopi.core.domain.usecase.MovieUseCase

class MoviesViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getPopularMovies().asLiveData()
}
