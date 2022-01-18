package com.example.myquizapp.ui.settings

import android.content.Intent
import android.net.Uri
import android.widget.*
import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.databinding.FragmentSettingsBinding
import com.example.myquizapp.utils.deleteDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    private lateinit var rateSheet: BottomSheetBehavior<LinearLayout>
    private lateinit var feedbackSheet: BottomSheetBehavior<FrameLayout>
    private lateinit var ratingBr: RatingBar
    private lateinit var tvRatingBar: TextView
    private lateinit var btnRate: Button
    private val viewModel: SettingsViewModel by viewModel()

    override fun bind(): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        ui.rate.setOnClickListener {
            showRateBottomSheet()
        }
        ui.share.setOnClickListener {
            hideBottomSheet()
            shareDialog()
        }
        ui.feedback.setOnClickListener {
            showFeedbackBottomSheet()
        }
        ui.clearHistory.setOnClickListener {
            hideBottomSheet()
            deleteDialog(requireContext(), "", getString(R.string.are_you_sure_all), this::clearAll)
        }
        ui.root.findViewById<Button>(R.id.btn_feedback_send).setOnClickListener {
            val text = ui.root.findViewById<EditText>(R.id.edt_feedback).text.toString()
            val mailIntent = Intent(Intent.ACTION_SENDTO)
            val uriText = "mailto:" + Uri.encode("miraidajaanbaeva@gmail.com") +
                    "?subject=" + Uri.encode("Feedback for QuizApp") +
                    "&body=" + Uri.encode(text)
            mailIntent.data = Uri.parse(uriText)
            requireActivity().startActivity(mailIntent, null)
        }
        btnRate = ui.root.findViewById(R.id.btn_rate)
        btnRate.setOnClickListener {
            val strValue: String = ratingBr.rating.toString()
            Toast.makeText(context, strValue, Toast.LENGTH_SHORT).show()
            showRateBottomSheet()
        }
    }

    private fun showFeedbackBottomSheet() {
        if (feedbackSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
            hideBottomSheet()
            feedbackSheet.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            feedbackSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun shareDialog() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            val shareMessage = "Check your knowledge with QuizApp!"
            putExtra(Intent.EXTRA_TEXT, shareMessage)
        }
        startActivity(Intent.createChooser(shareIntent, "Select app to share with"))
    }

    private fun showRateBottomSheet() {
        if (rateSheet.state != BottomSheetBehavior.STATE_EXPANDED) {
            hideBottomSheet()
            rateSheet.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            rateSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    override fun initView() {
        ratingBr = ui.root.findViewById(R.id.rating_bar)
        tvRatingBar = ui.root.findViewById(R.id.tv_rating_bar)
        rateSheet = BottomSheetBehavior.from(ui.root.findViewById(R.id.bottom_sheet_rate))
        feedbackSheet = BottomSheetBehavior.from(ui.root.findViewById(R.id.bottom_sheet_feedback))
    }

    private fun hideBottomSheet() {
        if (rateSheet.state != BottomSheetBehavior.STATE_HIDDEN)
            rateSheet.state = BottomSheetBehavior.STATE_HIDDEN

        if (feedbackSheet.state != BottomSheetBehavior.STATE_HIDDEN)
            feedbackSheet.state = BottomSheetBehavior.STATE_HIDDEN

    }

    private fun clearAll() {
        viewModel.deleteAll()
    }
}