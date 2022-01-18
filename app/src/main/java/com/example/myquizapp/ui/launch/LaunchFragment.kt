package com.example.myquizapp.ui.launch

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.example.myquizapp.R
import com.example.myquizapp.core.base.BaseFragment
import com.example.myquizapp.databinding.FragmentLaunchBinding


class LaunchFragment : BaseFragment<FragmentLaunchBinding>() {

    override fun bind(): FragmentLaunchBinding {
        return FragmentLaunchBinding.inflate(layoutInflater)
    }

    override fun setupListener() {

    }

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().popBackStack(R.id.navigation_launch, true)
            findNavController().navigate(R.id.navigation_home)
        }, 1200)
    }
}