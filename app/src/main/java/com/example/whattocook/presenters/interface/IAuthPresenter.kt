package com.example.whattocook.presenters.`interface`

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

interface IAuthPresenter {

    fun CreateUser(activity:AppCompatActivity, auth:FirebaseAuth, email:String, password:String,name:String)

    fun SignIn(activity: AppCompatActivity, auth:FirebaseAuth, email: String, password: String)

}