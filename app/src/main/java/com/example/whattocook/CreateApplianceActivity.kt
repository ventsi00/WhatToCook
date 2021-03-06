package com.example.whattocook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.CreateAppliancePresenter
import com.example.whattocook.presenters.ImagePresenter
import com.example.whattocook.presenters.UiPresenter
import com.google.firebase.auth.FirebaseAuth

private const val PiCK_IMAGE_REQUEST = 1
private var mediaName:String = ""
private var addProductResultUri: Uri? = null
private val imgPresenter = ImagePresenter()
private val caPresenter = CreateAppliancePresenter()

private lateinit var addPicture:Button
private lateinit var addAppliance:Button
private lateinit var image:ImageView
private lateinit var name:EditText
class CreateApplianceActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_appliance)

        addPicture = findViewById(R.id.buttonAddPhotoCreateAppliance)
        addAppliance = findViewById(R.id.buttonFinishCreateAppliance)
        image = findViewById(R.id.imageViewCreateAppliance)
        name = findViewById(R.id.editTextTextNameCreateAppliance)

        addPicture.setOnClickListener(clickListener)
        addAppliance.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonAddPhotoCreateAppliance ->
            {
                imgPresenter.imageSearch(this, PiCK_IMAGE_REQUEST)
            }

            R.id.buttonFinishCreateAppliance ->
            {
                if (name.text == null || name.text.toString() == "")
                {
                    Toast.makeText(this,R.string.NoNameWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }

                if (addProductResultUri == null)
                {
                    Toast.makeText(this,R.string.NoImageWarning,Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }

                mediaName = imgPresenter.uploadApplianceImageToDb(addProductResultUri!!,this)
                caPresenter.createAppliance(name.text.toString(), mediaName, currentUser.userId,this,resources.getString(R.string.ApplianceNameTakenWarning))
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PiCK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            addProductResultUri = data.data
            image.setImageURI(addProductResultUri)
        }
    }


}