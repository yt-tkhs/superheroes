package app.ytak.superheroes.di

import app.ytak.superheroes.comics.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComicsViewModel(get()) }
}