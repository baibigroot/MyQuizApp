package com.example.myquizapp.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquizapp.data.local.LocalRepository
import com.example.myquizapp.model.History

import kotlinx.coroutines.launch

class HistoryViewModel(private val localRepository: LocalRepository) : ViewModel() {

    val result = MutableLiveData<List<History>>()

    fun getAllHistories() {
        viewModelScope.launch {
            result.value = localRepository.getAllHistory()
        }
    }

    fun delete(history: History) {
        viewModelScope.launch {
            localRepository.delete(history)
        }
    }
}