package com.example.myquizapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myquizapp.data.local.LocalRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val localRepository: LocalRepository) : ViewModel() {

    fun deleteAll(){
        viewModelScope.launch {
            localRepository.deleteAll()
        }
    }
}