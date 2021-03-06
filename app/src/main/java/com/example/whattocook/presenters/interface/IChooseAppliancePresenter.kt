package com.example.whattocook.presenters.`interface`

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

interface IChooseAppliancePresenter {

     fun startRecyclerWithAllAppliances(recycler: RecyclerView, context: AppCompatActivity,list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser:Boolean)

     fun startRecyclerWithSomeAppliances(recycler: RecyclerView, inputString:String, context: AppCompatActivity, list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser: Boolean)

     fun getApplianceImage(name:String, view: ImageView)

     fun getApplianceImageProfile(name:String, view: ImageView)

     fun addAppliance(appliance:String,id:String)

     fun removeAppliance(appliance:String, id:String)

}