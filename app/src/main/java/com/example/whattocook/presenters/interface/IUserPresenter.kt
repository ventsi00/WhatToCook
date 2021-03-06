package com.example.whattocook.presenters.`interface`

import android.app.Dialog
import android.widget.ImageView
import android.widget.TextView
import com.example.whattocook.models.User

interface IUserPresenter {
    fun getUser(id:String,user: User)
    fun getName(id:String,text: TextView)
    fun getAvatarInstantly(id: String, view: ImageView, dialog: Dialog)
}