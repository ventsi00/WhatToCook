package com.example.whattocook.presenters

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.models.Recipe
import com.example.whattocook.models.User
import com.example.whattocook.presenters.`interface`.IImagePresenter
import com.example.whattocook.repository.DataBase
import com.example.whattocook.repository.Storage

class ImagePresenter:IImagePresenter {
    private val storage = Storage()
    private val db = DataBase()

    override fun getFileName(uri: Uri, context: Context): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor = context.contentResolver.query(uri, null, null, null, null)!!
            cursor.use { cursor ->
                if (cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result!!.substring(cut + 1)
            }
        }
        return result
    }

    override fun imageSearch(activity: AppCompatActivity,PiCK_IMAGE_REQUEST:Int)
    {
        val mIntent = Intent()
        mIntent.type = "image/*"
        mIntent.action = Intent.ACTION_GET_CONTENT
        activity.startActivityForResult(mIntent, PiCK_IMAGE_REQUEST)
    }

    override fun uploadToDb(uri:Uri, context: Context,user: User):String
    {
        val name = getFileName(uri,context)!! + "-" + System.currentTimeMillis()
        storage.uploadImage(name,uri,user)
        return name
    }

    override fun uploadProductImageToDb(uri:Uri, context: Context):String
    {
        val name = getFileName(uri,context)!! + "-" + System.currentTimeMillis()
        storage.uploadProductImage(name,uri)
        return name
    }

    override fun uploadApplianceImageToDb(uri:Uri, context: Context):String
    {
        val name = getFileName(uri,context)!! + "-" + System.currentTimeMillis()
        storage.uploadApplianceImage(name,uri)
        return name
    }

    override fun uploadRecipeImageToDb(uri: Uri, context: Context): String
    {
        val name = getFileName(uri,context)!! + "-" + System.currentTimeMillis()
        storage.uploadRecipeImage(name,uri)
        return name
    }

    override fun getImage(child:String, view:ImageView)
    {
        storage.getImage(child,view)
    }

    override fun updateAvatar(id: String, avatar: String, user: User)
    {
         db.updateAvatar(id,avatar,user)
    }
}