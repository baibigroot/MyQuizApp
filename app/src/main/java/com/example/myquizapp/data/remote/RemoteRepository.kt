package com.example.myquizapp.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myquizapp.core.network.Resource
import com.example.myquizapp.data.remote.QuizApi
import com.example.myquizapp.model.Questions
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

class RemoteRepository(private val quizApi: QuizApi) {

    fun getQuestions(
        amount: String,
        category: String,
        difficulty: String
    ): LiveData<Resource<Questions?>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))

        val response: Response<Questions> = if (category == "All" && difficulty == "All") {
            quizApi.getDefaultQuestions(amount)
        } else if (category != "All" && difficulty == "All") {
            quizApi.getQuestionsWithCategory(amount, category)
        } else if (category == "All" && difficulty != "All") {
            quizApi.getQuestionsWithDifficulty(amount, difficulty)
        } else {
            quizApi.getQuestionsWithAll(amount, category, difficulty)
        }

        emit(
            if (response.isSuccessful) Resource.success(response.body(), response.code())
            else Resource.error(response.message(), response.code())
        )
    }

}