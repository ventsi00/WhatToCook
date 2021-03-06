package com.example.whattocook.presenters.`interface`

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.R
import java.util.*

interface ISpeechPresenter {
    fun speechInput(context: AppCompatActivity,message:String,result:Int)
}