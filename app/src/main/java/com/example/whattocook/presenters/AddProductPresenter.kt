package com.example.whattocook.presenters

import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.`interface`.IAddProductPresenter
import com.example.whattocook.repository.DataBase

class AddProductPresenter: IAddProductPresenter {
    private val db = DataBase()

    override fun createProduct(name: String,media:String,measure:String,userId:String,activity:AppCompatActivity,message:String)
    {
        db.createProduct(name,media,measure,userId,activity,message)
    }

}