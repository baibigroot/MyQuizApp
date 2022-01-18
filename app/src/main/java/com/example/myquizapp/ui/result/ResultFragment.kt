package com.example.myquizapp.ui.result


import androidx.navigation.fragment.findNavController
import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.databinding.FragmentResultBinding
import com.example.myquizapp.exten.visibility
import com.example.myquizapp.model.History
import com.example.myquizapp.utils.Constants
import com.google.gson.Gson

class ResultFragment : BaseFragment<FragmentResultBinding>() {
    lateinit var history: History

    override fun bind(): FragmentResultBinding {
        return FragmentResultBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        ui.finish.setOnClickListener {
            findNavController().popBackStack(R.id.navigation_result, true)
            findNavController().navigate(R.id.navigation_home)
        }
    }

    override fun initView() {
        if (arguments != null)
            history = Gson().fromJson(
                requireArguments().getString(Constants.RESULT_KEY),
                History::class.java
            )
        setData()
    }

    private fun setData() {
        val amount = history.amount?.toInt()
        val score = (history.correctAnswers * 100) / amount!!

        if (score > 50) ui.ivCorrect.visibility(true)

        ui.tvCategoryResult.text = resources.getString(R.string.category_,history.category)
        ui.tvDiff.text = history.difficulty
        val correctAns = history.correctAnswers
        "$correctAns/$amount".also { ui.tvCorrectAns.text = it }
        "$score%".also { ui.tvResult.text = it }

    }

}