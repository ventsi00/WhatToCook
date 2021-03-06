package com.example.whattocook.presenters

import android.content.Context
import com.example.whattocook.models.ModelAny
import com.example.whattocook.presenters.`interface`.IAdditionPresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

private val storage = Storage()
class AdditionPresenter:IAdditionPresenter {
    override fun deleteProduct(product:String, img:String, context: Context, message:String,itemList:MutableList<ModelAny>, position:Int)
    {
        storage.removeProduct(product, img, context, message, itemList, position)
    }

    override fun deleteAppliance(appliance:String, img:String, context: Context, message:String,itemList:MutableList<ModelAny>,position:Int)
    {
        storage.removeAppliance(appliance, img, context, message, itemList, position)
    }

    override fun deleteRecipe(recipe:String, img:String,itemList:MutableList<ModelAny>,position:Int)
    {
      storage.removeRecipe(recipe, img, itemList, position)
    }
}