package com.example.myquizapp.data.local

import com.example.myquizapp.data.local.HistoryDao
import com.example.myquizapp.model.History

class LocalRepository(private val historyDao: HistoryDao) {

    suspend fun getAllHistory(): List<History> {
        return historyDao.getAllHistory()
    }

    suspend fun deleteAll() {
        historyDao.deleteAll()
    }

    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    suspend fun delete(history: History) {
        historyDao.delete(history)
    }
}