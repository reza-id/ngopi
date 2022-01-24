package dev.cianjur.ngopi.favorite.di

import dev.cianjur.ngopi.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get(), get()) }
}
