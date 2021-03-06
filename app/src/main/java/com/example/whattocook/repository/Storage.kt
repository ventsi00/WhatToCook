package com.example.whattocook.repository

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.ModelAny
import com.example.whattocook.models.Recipe
import com.example.whattocook.models.User
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class Storage {
    private val storageRef: StorageReference = Firebase.storage.getReference("Images")
    private val productStorageRef: StorageReference = Firebase.storage.getReference("ProductImages")
    private val applianceStorageRef: StorageReference = Firebase.storage.getReference("ApplianceImages")
    private val recipeStorageRef: StorageReference = Firebase.storage.getReference("RecipeImages")
    private var db = DataBase()

    fun uploadImage(name:String,uri:Uri,user: User)
    {
        deleteImage(user)
        val fileRef = storageRef.child(name)
        fileRef.putFile(uri)
    }

    fun uploadProductImage(name:String,uri:Uri)
    {
        val fileRef = productStorageRef.child(name)
        fileRef.putFile(uri)
    }

    fun uploadApplianceImage(name:String,uri:Uri)
    {
        val fileRef = applianceStorageRef.child(name)
        fileRef.putFile(uri)
    }

    fun uploadRecipeImage(name:String,uri:Uri)
    {
        val fileRef = recipeStorageRef.child(name)
        fileRef.putFile(uri)
    }

    fun getImage(child:String,view: ImageView)
    {
        db.getAvatar(child,storageRef,view)
    }

    fun getProductImage(name:String,view: ImageView)
    {
        db.getImage(name,productStorageRef,view)
    }

    fun getApplianceImage(name:String,view: ImageView)
    {
        db.getImage(name,applianceStorageRef,view)
    }

    fun getApplianceImageProfile(name:String,view: ImageView)
    {
        db.getApplianceImageProfile(name,applianceStorageRef,view)
    }

    fun getImageInstantly(id:String,view: ImageView,dialog:Dialog)
    {
        db.getAvatarInstantly(id,storageRef,view,dialog)
    }

    fun getRecipeImage(id:String,view: ImageView)
    {
        db.getImage(id,recipeStorageRef,view)
    }

    private fun deleteImage(user: User)
    {
        if (user.avatar != "avatar.png")
        {
            storageRef.child(user.avatar).delete()
        }
    }

    fun loadRecipe(name:String, image:String, nameText: TextView, description: TextView, products: ListView, appliances: ListView, context: Context, imageView: ImageView)
    {
        db.loadRecipe(name,image,nameText,description,products,appliances,context,recipeStorageRef,imageView)
    }

    fun removeAppliance(appliance: String, img:String, context: Context, message:String, itemList:MutableList<ModelAny>, position:Int)
    {
        db.deleteAppliance(appliance,context,message,itemList,position,applianceStorageRef.child(img))
    }

    fun removeProduct(product:String,img: String,context: Context,message: String,itemList:MutableList<ModelAny>,position:Int)
    {
        db.deleteProduct(product,context,message,itemList,position,productStorageRef.child(img))
    }

    fun removeRecipe(recipe:String,img:String,itemList:MutableList<ModelAny>,position:Int)
    {
        db.deleteRecipe(recipe,itemList,position, recipeStorageRef.child(img))
    }

}