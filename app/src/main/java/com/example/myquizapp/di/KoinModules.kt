package com.example.myquizapp.di

import com.example.myquizapp.data.remote.networkModule

val koinModules = listOf(
    repoModule,
    viewModelModules,
    roomModule,
    networkModule
)