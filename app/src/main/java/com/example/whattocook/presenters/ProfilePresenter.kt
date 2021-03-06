package com.example.whattocook.presenters

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance
import com.example.whattocook.presenters.`interface`.IProfilePresenter
import com.example.whattocook.repository.DataBase

class ProfilePresenter:IProfilePresenter {
    private val db = DataBase()

    override fun startRecyclerWithUserAppliances(recyclerView: RecyclerView, context: AppCompatActivity, id:String,applianceList: MutableList<Appliance>)
    {
        db.startRecyclerWithUserAppliances(recyclerView,context,id,applianceList)
    }
}