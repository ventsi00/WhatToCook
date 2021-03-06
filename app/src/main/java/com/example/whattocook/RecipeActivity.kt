package com.example.whattocook

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.RecipePresenter

private val presenter = RecipePresenter()

private lateinit var nameText:TextView
private lateinit var imageView:ImageView
private lateinit var products:ListView
private lateinit var appliances:ListView
private lateinit var description:TextView
class RecipeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_chosen)

        nameText = findViewById(R.id.textViewNameRecipeChosen)
        imageView = findViewById(R.id.imageViewRecipeChosen)
        products = findViewById(R.id.ListViewProductsRecipeChosen)
        appliances = findViewById(R.id.listAppliancesRecipeChosen)
        description = findViewById(R.id.textViewDescriptionRecipeChosen)

        val name = intent.getStringExtra("RecipeName")
        val image = intent.getStringExtra("RecipeImage")

        presenter.loadRecipe(name!!, image!!,nameText, description, products, appliances,this, imageView)
    }
}