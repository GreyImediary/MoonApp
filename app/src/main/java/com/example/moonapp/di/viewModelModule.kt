package com.example.moonapp.di

import com.example.moonapp.viewModels.MainScreenViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { MainScreenViewModel(get()) }
}