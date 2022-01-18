package com.example.myquizapp.exten

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun ImageView.loadImage(res: Boolean) {
    if (res)
        Glide.with(this)
            .load("https://www.nicepng.com/png/detail/362-3624869_icon-success-circle-green-tick-png.png")
            .into(this)
    else
        Glide.with(this)
            .load("https://www.arttdinox.com/assets/assets/web/wrong.gif")
            .into(this)
}