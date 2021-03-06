package com.example.whattocook.presenters

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.`interface`.IChooseAppliancePresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

class ChooseAppliancePresenter:IChooseAppliancePresenter {
    private val db = DataBase()
    private val storage = Storage()

    override fun startRecyclerWithAllAppliances(recycler:RecyclerView, context: AppCompatActivity, list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser:Boolean)
    {
        db.startRecyclerWithAllAppliances(recycler,context,list,targetRecycler,addToUser)
    }

    override fun startRecyclerWithSomeAppliances(recycler:RecyclerView, inputString:String, context: AppCompatActivity,list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser: Boolean)
    {
        db.startRecyclerWithSomeAppliances(recycler,inputString,context,list,targetRecycler,addToUser)
    }

    override fun getApplianceImage(name:String, view: ImageView)
    {
        storage.getApplianceImage(name,view)
    }

    override fun getApplianceImageProfile(name: String, view: ImageView) {
        storage.getApplianceImageProfile(name,view)
    }

    override fun addAppliance(appliance:String, id:String)
    {
        db.addAppliance(appliance,id)
    }

    override fun removeAppliance(appliance:String, id:String)
    {
        db.removeAppliance(appliance,id)
    }

}