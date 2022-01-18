package com.example.myquizapp.di

import android.content.Context
import androidx.room.Room
import com.example.myquizapp.data.local.AppDataBase
import com.example.myquizapp.data.local.HistoryDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

var roomModule = module {
    single { getAppDatabase(androidContext()) }
    single { getHistoryDao(get()) }
}

private fun getAppDatabase(applicationContext: Context) =
    Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java,
        "Quiz Database").build()


private fun getHistoryDao(appDataBase: AppDataBase): HistoryDao {
    return appDataBase.historyDao()
}