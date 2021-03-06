package com.example.whattocook.presenters.`interface`

import androidx.appcompat.app.AppCompatActivity

interface ICreateAppliancePresenter {
    fun createAppliance(name: String, media:String, userId:String, activity: AppCompatActivity, message:String)
}