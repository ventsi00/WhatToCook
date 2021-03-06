package com.example.whattocook.presenters

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.`interface`.IChooseProductPresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

class ChooseProductPresenter: IChooseProductPresenter {
    private val db = DataBase()
    private val storage = Storage()

    override fun startRecyclerWithAllProducts(recycler:RecyclerView, context: AppCompatActivity, list: MutableList<Product>,targetRecycler:RecyclerView)
    {
     db.startRecyclerWithAllProducts(recycler,context,list,targetRecycler)
    }

    override fun startRecyclerWithSomeProducts(recycler:RecyclerView, inputString:String, context: AppCompatActivity,list: MutableList<Product>,targetRecycler:RecyclerView)
    {
        db.startRecyclerWithSomeProducts(recycler,inputString,context,list,targetRecycler)
    }

    override fun getProductImage(name:String, view: ImageView)
    {
        storage.getProductImage(name,view)
    }
}