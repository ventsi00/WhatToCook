package com.example.whattocook.presenters

import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.`interface`.ICreateAppliancePresenter
import com.example.whattocook.repository.DataBase

class CreateAppliancePresenter:ICreateAppliancePresenter {
    private val db = DataBase()
    override fun createAppliance(name: String, media: String, userId: String, activity: AppCompatActivity, message: String) {
        db.createAppliance(name,media,userId,activity,message)
    }
}