package com.example.whattocook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.UiPresenter
import kotlin.system.exitProcess

class NoInternetActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_internet)
    }

    override fun onBackPressed() {

    }
}