package com.example.whattocook.presenters

import android.app.Dialog
import android.widget.ImageView
import android.widget.TextView
import com.example.whattocook.models.User
import com.example.whattocook.presenters.`interface`.IUserPresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

class UserPresenter:IUserPresenter {
    private val db = DataBase()
    private val storage = Storage()

    override fun getUser(id:String, user: User)
    {
        db.getUser(id,user)
    }

    override fun getName(id:String, text:TextView)
    {
        db.getName(id, text)
    }

    override fun getAvatarInstantly(id: String, view:ImageView, dialog: Dialog)
    {
        storage.getImageInstantly(id,view,dialog)
    }

}