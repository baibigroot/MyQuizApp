package com.example.myquizapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.engine.Resource
import com.example.myquizapp.data.remote.RemoteRepository
import com.example.myquizapp.model.Questions

class HomeViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    val loadingProgressBar = MutableLiveData<Boolean>()
    fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ): LiveData<com.example.myquizapp.core.network.Resource<Questions?>> {
        return remoteRepository.getQuestions(amount, category, difficulty)
    }
}