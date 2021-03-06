package com.example.whattocook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.adapters.RecyclerAdapterChooseProduct
import com.example.whattocook.adapters.RecyclerAdapterHome
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.ChooseProductPresenter
import com.example.whattocook.presenters.SpeechPresenter
import com.example.whattocook.repository.DataBase
import java.util.*

private lateinit var target:String

private val cpPresenter = ChooseProductPresenter()

private lateinit var search:EditText
private lateinit var btnSearch:Button
private lateinit var btnVoice:Button
private lateinit var recycler:RecyclerView

private val voicePresenter = SpeechPresenter()
private const val resultForSpeech = 100

class ChooseProductActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_product)

        target = intent.getStringExtra("List")!!

        btnSearch = findViewById(R.id.buttonChooseProductSearch)
        btnVoice = findViewById(R.id.buttonVoiceSearch)
        search = findViewById(R.id.editTextChooseProduct)
        recycler = findViewById(R.id.recyclerViewChooseProduct)

        btnVoice.setOnClickListener{
            voicePresenter.speechInput(this,resources.getString(R.string.VoiceSearchError), resultForSpeech)
        }



        when (target) {
            "home" ->
            {
                cpPresenter.startRecyclerWithAllProducts(recycler,this, productList, recyclerViewHome)
            }
            "createRecipe" ->
            {
                cpPresenter.startRecyclerWithAllProducts(recycler,this, listProductsCreateRecipe, recyclerViewProductsCreateRecipe )
            }
            else -> {
                Log.d("Error","ChooseProduct Not Working")
            }
        }


        recycler.layoutManager = GridLayoutManager(this, 2)

        btnSearch.setOnClickListener{

            when (target) {
                "home" ->
                {
                    cpPresenter.startRecyclerWithSomeProducts(recycler, search.text.toString().toLowerCase(Locale.ROOT),this, productList, recyclerViewHome)

                }
                "createRecipe" ->
                {
                    cpPresenter.startRecyclerWithSomeProducts(recycler, search.text.toString().toLowerCase(Locale.ROOT),this, listProductsCreateRecipe, recyclerViewProductsCreateRecipe)
                }
                else -> {
                    Log.d("Error","ChooseProduct Not Working")
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == resultForSpeech && resultCode == Activity.RESULT_OK)
        {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            search.setText(result?.get(0).toString())
            btnSearch.performClick()
        }
    }
}