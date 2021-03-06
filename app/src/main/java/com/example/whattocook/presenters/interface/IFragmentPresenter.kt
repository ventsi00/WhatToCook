package com.example.whattocook.presenters.`interface`

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

interface IFragmentPresenter {
    fun getProducts(view: RecyclerView, context: Context, id:String)
    fun getAppliances(view:RecyclerView,context: Context,id:String)
    fun getRecipe(view:RecyclerView,context: Context,id:String)
}