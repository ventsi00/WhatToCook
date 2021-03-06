package com.example.whattocook.presenters

import android.content.Context
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.currentUser
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.`interface`.IRecipePresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

class RecipePresenter:IRecipePresenter {
    private val db = DataBase()
    private val storage = Storage()
    override fun createRecipe(name:String, media:String, description:String, productList:MutableList<Product>, applianceList:MutableList<Appliance>
                              , context: Context, message:String)
    {
        db.createRecipe(name,media,description,applianceList,productList,context,message, currentUser.userId)
    }

    override fun getRecipeImage(id: String, view: ImageView) {
        storage.getRecipeImage(id,view)
    }

    override fun startRecyclerWithSearchResults(recycler: RecyclerView, productList:MutableList<Product>,username:String,context: Context)
    {
        db.startRecyclerWithRecipes(recycler,productList,username,context)
    }

    override fun loadRecipe(name:String, image:String, nameText:TextView, description:TextView, products:ListView, appliances:ListView, context: Context, imageView: ImageView)
    {
       storage.loadRecipe(name,image,nameText,description, products, appliances, context, imageView)
    }


}