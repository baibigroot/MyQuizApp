package com.example.myquizapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val category: String?,
    var correctAnswers: Int,
    val difficulty: String?,
    val amount:String?,
    val date: Long)


