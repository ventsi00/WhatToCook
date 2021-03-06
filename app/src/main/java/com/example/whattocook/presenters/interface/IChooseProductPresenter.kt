package com.example.whattocook.presenters.`interface`

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Product

interface IChooseProductPresenter {

    fun startRecyclerWithAllProducts(recycler: RecyclerView, context: AppCompatActivity,list: MutableList<Product>,targetRecycler:RecyclerView)

    fun startRecyclerWithSomeProducts(recycler:RecyclerView, inputString:String, context: AppCompatActivity,list: MutableList<Product>,targetRecycler:RecyclerView)

    fun getProductImage(name:String,view: ImageView)
}