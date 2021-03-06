package com.example.whattocook.presenters

import com.example.whattocook.presenters.`interface`.IProductPresenter
import com.example.whattocook.repository.DataBase

class ProductPresenter:IProductPresenter {
    private val db = DataBase()

    override fun getAllProducts()
    {

    }
}