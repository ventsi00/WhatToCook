package com.example.whattocook.presenters.`interface`

import androidx.appcompat.app.AppCompatActivity

interface IAddProductPresenter {
    fun createProduct(name: String,media:String,measure:String,userId:String,activity:AppCompatActivity,message:String)
}