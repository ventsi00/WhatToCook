package com.example.whattocook.presenters

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.whattocook.HomeActivity
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.models.User
import com.example.whattocook.presenters.`interface`.IUiPresenter

class UiPresenter:IUiPresenter {
//    override fun changeUi(context: Context, user: User, activity: AppCompatActivity) {
//        val intent = Intent(context, activity::class.java)
//        intent.putExtra("UID", user)
//        ContextCompat.startActivity(context, intent, null)
//    }

    override fun changeUi(context: Context, activity: AppCompatActivity) {
        val mIntent = Intent(context, activity::class.java)
        ContextCompat.startActivity(context, mIntent, null)
    }

    override fun changeUi(context: Context, activity: AppCompatActivity,extra: String) {
        val intent = Intent(context, activity::class.java)
        intent.putExtra("List",extra)
        ContextCompat.startActivity(context, intent, null)
    }

    override fun changeUi(context: Context, activity: AppCompatActivity,name: String,image:String) {
        val intent = Intent(context, activity::class.java)
        intent.putExtra("RecipeName",name)
        intent.putExtra("RecipeImage",image)
        ContextCompat.startActivity(context, intent, null)
    }

}