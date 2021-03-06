package com.example.whattocook.presenters.`interface`

import android.content.Context
import com.example.whattocook.models.ModelAny

interface IAdditionPresenter {
    fun deleteProduct(product:String, img:String, context: Context, message:String,itemList:MutableList<ModelAny>, position:Int)
    fun deleteAppliance(appliance:String,img:String,context: Context,message:String,itemList:MutableList<ModelAny>,position:Int)
    fun deleteRecipe(recipe:String,img:String,itemList:MutableList<ModelAny>,position:Int)
}