package com.demo.movies.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.movies.R

/* Base Activity Class */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isPortraitOnly(this)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    companion object {
        private fun isPortraitOnly(context: Context): Boolean {
            return context.resources.getBoolean(R.bool.portrait_only)
        }
    }
}
