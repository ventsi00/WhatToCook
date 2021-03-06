package com.example.whattocook.presenters.`interface`

import android.content.Context
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.models.User

interface IUiPresenter {
//    fun changeUi(context: Context,user: User,activity:AppCompatActivity)
    fun changeUi(context: Context,activity:AppCompatActivity)
    fun changeUi(context: Context, activity: AppCompatActivity,extra: String)
    fun changeUi(context: Context, activity: AppCompatActivity,name: String,image:String)
}