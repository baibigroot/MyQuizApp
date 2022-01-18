package com.example.myquizapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.core.network.Status
import com.example.myquizapp.databinding.FragmentHomeBinding
import com.example.myquizapp.exten.visibility
import com.example.myquizapp.model.Questions
import com.example.myquizapp.utils.Constants
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var amount: String = "10"
    private var category: String = "All"
    private var diff: String = "All"
    private var categoryName: String = "All"
    private lateinit var options1 :ArrayList<String>
    private lateinit var options2 :ArrayList<String>
    private val viewModel: HomeViewModel by viewModel()

    override fun bind(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }
    override fun setupListener() {
        ui.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoryName = options1[position]

                category = if (position == 0) options1[0]
                else (8 + position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                category = options1[0]
            }
        }

        ui.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                diff = options2[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                diff = options2[0]
            }
        }
        ui.mySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ui.questionsAmount.text = getString(R.string.questions_amount, progress.toString())
                amount = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                amount = seekBar?.progress.toString()
            }
        })

        ui.btnStart.setOnClickListener {
            getData()
        }
        viewModel.loadingProgressBar.observe(viewLifecycleOwner,{
            ui.progressBar.visibility(it)
        })

    }

    private fun getData() {
        viewModel.getQuestions(amount,category,diff).observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    viewModel.loadingProgressBar.postValue(true)
                }
                Status.SUCCESS->{
                    viewModel.loadingProgressBar.postValue(false)
                    openQuizFragment(it.data)
                }
                Status.ERROR->{
                    viewModel.loadingProgressBar.postValue(false)
                    Toast.makeText(requireContext(),"Something wrong...",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openQuizFragment(data: Questions?) {
        val bundle = Bundle()
        bundle.putString(Constants.DATA_KEY,Gson().toJson(data))
        bundle.putString(Constants.CATEGORY_KEY,categoryName)
        bundle.putString(Constants.DIFFICULTY_KEY,diff)
        findNavController().popBackStack(R.id.navigation_home,true)
        findNavController().navigate(R.id.navigation_quiz,bundle)
    }


    override fun initView() {
        ui.questionsAmount.text = getString(R.string.questions_amount, "10")

        options1 = resources.getStringArray(R.array.category_array).toList() as ArrayList<String>
        options2 = resources.getStringArray(R.array.difficulty_array).toList() as ArrayList<String>

        ui.spinner1.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options1)
        ui.spinner2.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options2)
    }


}