package dev.cianjur.ngopi.di

import dev.cianjur.ngopi.core.domain.usecase.MovieInteractor
import dev.cianjur.ngopi.core.domain.usecase.MovieUseCase
import dev.cianjur.ngopi.core.domain.usecase.TvShowInteractor
import dev.cianjur.ngopi.core.domain.usecase.TvShowUseCase
import dev.cianjur.ngopi.presentation.detail.DetailViewModel
import dev.cianjur.ngopi.presentation.movies.MoviesViewModel
import dev.cianjur.ngopi.presentation.tvshows.TvShowsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { TvShowsViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}
