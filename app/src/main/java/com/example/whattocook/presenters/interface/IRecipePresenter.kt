package com.example.whattocook.presenters.`interface`

import android.content.Context
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product

interface IRecipePresenter {
    fun createRecipe(name:String, media:String, description:String, productList:MutableList<Product>, applianceList:MutableList<Appliance>
                     , context: Context, message:String)

    fun getRecipeImage(id:String,view:ImageView)

    fun startRecyclerWithSearchResults(recycler: RecyclerView, productList:MutableList<Product>,username:String,context: Context)

    fun loadRecipe(name:String, image:String, nameText: TextView, description: TextView, products: ListView, appliances: ListView, context: Context, imageView: ImageView)


}