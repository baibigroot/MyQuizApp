package com.example.myquizapp.ui.history

import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.databinding.FragmentHistoryBinding
import com.example.myquizapp.exten.visibility
import com.example.myquizapp.model.History
import com.example.myquizapp.utils.deleteDialog
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()
    private lateinit var history: History
    private lateinit var adapter: HistoryAdapter

    override fun bind(): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun setupListener() {

    }

    override fun initView() {
        viewModel.getAllHistories()
        viewModel.result.observe(viewLifecycleOwner, {
            if (it.isEmpty()) ui.tvEmpty.visibility(true)
            else initAdapter(it)
        })
    }

    private fun initAdapter(it: List<History>) {
        adapter = HistoryAdapter(it, this::onItemClick)
        ui.rvHistory.adapter = adapter
    }

    private fun onItemClick(history: History) {
        this.history = history
        deleteDialog(requireContext(), "", getString(R.string.are_you_sure), this::delete)
    }

    private fun delete() {
        viewModel.delete(history)
        viewModel.getAllHistories()
    }
}