package com.example.whattocook.presenters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.presenters.`interface`.IFragmentPresenter
import com.example.whattocook.repository.DataBase

class FragmentPresenter:IFragmentPresenter {
    private val db = DataBase()

    override fun getProducts(view:RecyclerView,context: Context,id:String)
    {
        db.getAddedProducts(view,context,id)
    }

    override fun getAppliances(view: RecyclerView,context: Context,id: String)
    {
        db.getAddedAppliances(view,context,id)
    }

    override fun getRecipe(view: RecyclerView,context: Context,id: String)
    {
        db.getAddedRecipes(view,context,id)
    }
}