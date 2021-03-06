package com.example.whattocook.presenters.`interface`

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.models.Recipe
import com.example.whattocook.models.User

interface IImagePresenter {
    fun getFileName(uri: Uri, context: Context): String?

    fun imageSearch(activity: AppCompatActivity,PiCK_IMAGE_REQUEST:Int)

    fun uploadToDb(uri:Uri,context: Context,user: User):String

    fun uploadProductImageToDb(uri:Uri,context: Context):String

    fun uploadApplianceImageToDb(uri:Uri, context: Context):String

    fun uploadRecipeImageToDb(uri:Uri, context: Context):String

    fun getImage(child:String, view: ImageView)

    fun updateAvatar(id: String, avatar: String, user: User)
}