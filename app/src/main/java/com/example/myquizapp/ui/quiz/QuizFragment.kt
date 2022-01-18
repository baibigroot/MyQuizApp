package com.example.myquizapp.ui.quiz

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.databinding.QuizFragmentBinding
import com.example.myquizapp.exten.loadImage
import com.example.myquizapp.exten.visibility
import com.example.myquizapp.model.History
import com.example.myquizapp.model.Questions
import com.example.myquizapp.model.Result
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.myquizapp.utils.Constants
import com.google.gson.Gson

class QuizFragment : BaseFragment<QuizFragmentBinding>() {

    private var results: List<Result>? = null
    private lateinit var history: History
    private var pos: Int = 0
    private lateinit var corrAns: String
    private var size = 0
    private val viewModel: QuizViewModel by viewModel()

    override fun bind(): QuizFragmentBinding {
        return QuizFragmentBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        ui.btn1.setOnClickListener { checkData(corrAns == ui.btn1.text) }
        ui.btn2.setOnClickListener { checkData(corrAns == ui.btn2.text) }
        ui.btn3.setOnClickListener { checkData(corrAns == ui.btn3.text) }
        ui.btn4.setOnClickListener { checkData(corrAns == ui.btn4.text) }

        ui.btn1Tf.setOnClickListener { checkData(corrAns == ui.btn1Tf.text) }
        ui.btn2Tf.setOnClickListener { checkData(corrAns == ui.btn2Tf.text) }
        ui.btnSkip.setOnClickListener { checkData(false) }

        ui.ivBack.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun checkData(isCorrect: Boolean) {
        if (isCorrect) {
            viewModel.setCorrectAns()
        }
        ui.ivIsCorrect.visibility(true)
        ui.ivIsCorrect.loadImage(isCorrect)

        Handler(Looper.getMainLooper()).postDelayed({
            startGame()
        }, 1000)
    }

    override fun initView() {
        if (arguments != null) {
            results = Gson().fromJson(
                requireArguments().getString(Constants.DATA_KEY),
                Questions::class.java
            ).results

            history = History(
                0,
                requireArguments().getString(Constants.CATEGORY_KEY),
                0,
                requireArguments().getString(Constants.DIFFICULTY_KEY),
                results?.size.toString(),
                System.currentTimeMillis()
            )
            startGame()
        }
    }

    private fun startGame() {
        pos = viewModel.getPosition()
        size = results?.size!!

        if (size > pos) {
            setData(pos)
        } else {
            saveData()
        }
    }

    private fun setData(pos: Int) {
        val result = results?.get(pos)
        corrAns = checkText(result?.correct_answer.toString())

        ui.ivIsCorrect.visibility(false)
        ui.tvQuestion.text = checkText(result?.question)
        ui.tvCategory.text = result?.category
        ui.progressHorizontal.max = size
        val position = pos + 1
        ui.progressHorizontal.progress = position

        "$pos/$size".also { ui.tvProgress.text = it }

        if (result?.type == "multiple") {
            ui.multipleLayout.visibility(true)
            ui.trueFalseLayout.visibility(false)

            val answers = (result.incorrect_answers as ArrayList<String>?)!!
            answers.add(result.correct_answer.toString())
            answers.shuffle()

            ui.btn1.text = checkText(answers[0])
            ui.btn2.text = checkText(answers[1])
            ui.btn3.text = checkText(answers[2])
            ui.btn4.text = checkText(answers[3])

        } else {
            ui.multipleLayout.visibility(false)
            ui.trueFalseLayout.visibility(true)
        }
    }

    private fun saveData() {
        history.correctAnswers = viewModel.getCorrectAns()
        viewModel.insert(history)

        openResultFragment()
    }

    private fun openResultFragment() {
        val bundle = Bundle()
        bundle.putString(Constants.RESULT_KEY, Gson().toJson(history))

        findNavController().popBackStack(R.id.navigation_quiz, true)
        findNavController().navigate(R.id.navigation_result, bundle)
    }

    private fun checkText(text: String?): String {
        var newText: String = text?.replace("&quot;", "\"").toString()
        newText = newText.replace("&#039;", "'")

        return newText
    }
}