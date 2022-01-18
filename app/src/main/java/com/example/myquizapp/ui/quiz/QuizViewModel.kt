package com.example.myquizapp.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquizapp.data.local.LocalRepository
import com.example.myquizapp.model.History

import kotlinx.coroutines.launch

class QuizViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val position = MutableLiveData<Int>()
    private val correctAns = MutableLiveData<Int>()

    private var pos = 0
    private var ans = 0

    fun getPosition(): Int {
        position.value = pos++
        return position.value!!
    }

    fun setCorrectAns() {
        correctAns.value = ++ans
    }

    fun getCorrectAns(): Int {
        return if (correctAns.value != null) correctAns.value!! else 0
    }

    fun insert(history: History) {
        viewModelScope.launch {
            localRepository.insert(history)
        }
    }
}