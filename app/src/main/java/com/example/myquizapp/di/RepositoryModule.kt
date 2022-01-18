package com.example.myquizapp.di

import com.example.myquizapp.data.local.LocalRepository
import com.example.myquizapp.data.remote.RemoteRepository
import org.koin.dsl.module

val repoModule = module {
    single { LocalRepository(get()) }
    single { RemoteRepository(get()) }
}