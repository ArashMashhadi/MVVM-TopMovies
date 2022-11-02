package com.example.kotlintopmovies2.utils

import android.view.View

fun View.showInvisible(isShown: Boolean) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}