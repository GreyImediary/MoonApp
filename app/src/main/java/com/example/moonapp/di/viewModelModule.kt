package com.example.moonapp.di

import com.example.moonapp.viewModels.MainScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * DI на Koin для инстанциирования viewModel
 * @see [Koin](https://doc.insert-koin.io/#/koin-android/viewmodel)
 * */

val vmModule = module {
    viewModel { MainScreenViewModel(get()) }
}