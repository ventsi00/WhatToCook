package com.example.whattocook.presenters.`interface`

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance

interface IProfilePresenter {

    fun startRecyclerWithUserAppliances(recyclerView: RecyclerView, context: AppCompatActivity, id:String,applianceList:MutableList<Appliance>)
}