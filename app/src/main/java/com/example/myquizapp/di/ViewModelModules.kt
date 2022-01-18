package com.example.myquizapp.di

import com.example.myquizapp.ui.history.HistoryViewModel
import com.example.myquizapp.ui.quiz.QuizViewModel
import com.example.myquizapp.ui.settings.SettingsViewModel
import com.example.myquizapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { HistoryViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { QuizViewModel(get()) }
}