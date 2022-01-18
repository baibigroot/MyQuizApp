package com.example.myquizapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myquizapp.model.History

@Dao
interface HistoryDao {
    @Query("select * from history")
    suspend fun getAllHistory(): List<History>

    @Query("delete from history")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(history: History)

    @Delete
    suspend fun delete(history: History)

}