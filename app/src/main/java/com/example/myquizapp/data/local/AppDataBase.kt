package com.example.myquizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myquizapp.model.History

@Database (entities = [History::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}