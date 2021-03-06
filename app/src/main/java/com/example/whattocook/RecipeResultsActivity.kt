package com.example.whattocook

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.presenters.RecipePresenter

private val recipePresenter = RecipePresenter()

private lateinit var recycler:RecyclerView

class RecipeResultsActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_results)

        recycler = findViewById(R.id.recyclerRecipeResults)

        recipePresenter.startRecyclerWithSearchResults(recycler,productList, currentUser.userId,this)
        recycler.layoutManager = GridLayoutManager(this,2)
    }
}