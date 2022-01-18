package com.example.myquizapp.utils

import android.app.AlertDialog
import android.content.Context
import com.example.myquizapp.R


fun deleteDialog(context: Context, title: String, msg: String, listener: () -> Unit) {
    AlertDialog.Builder(context)
        .setIcon(R.drawable.ic_delete_24)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton("Ok") { _, _ ->
            listener()
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }.show()
}