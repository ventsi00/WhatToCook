package com.example.whattocook.presenters

import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.`interface`.ISpeechPresenter
import java.util.*

class SpeechPresenter:ISpeechPresenter {
    override fun speechInput(context: AppCompatActivity, message: String, result: Int) {
            if (!SpeechRecognizer.isRecognitionAvailable(context))
            {
                Toast.makeText(context,message, Toast.LENGTH_LONG).show()
            }
            else
            {
                val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                context.startActivityForResult(i,result)
            }
    }
}