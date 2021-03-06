package com.example.whattocook

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.presenters.AddProductPresenter
import com.example.whattocook.presenters.ImagePresenter
import com.example.whattocook.presenters.UiPresenter


private const val PiCK_IMAGE_REQUEST = 1

private val addProductPresenter = AddProductPresenter()
private val imgPresenter = ImagePresenter()

private var mediaName:String = ""
private var addProductResultUri: Uri? = null

private lateinit var btnAddPhoto:Button
private lateinit var btnAdd:Button
private lateinit var img:ImageView
private lateinit var name:EditText
private lateinit var radioGroup:RadioGroup

class AddProductActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)

        btnAddPhoto = findViewById(R.id.buttonAddPhotoAddProduct)
        btnAdd = findViewById(R.id.buttonFinishAddProduct)
        img = findViewById(R.id.imageViewAddProduct)
        name = findViewById(R.id.editTextTextNameAddProduct)
        radioGroup = findViewById(R.id.radioGroupAddProduct)

        btnAddPhoto.setOnClickListener(clickListener)
        btnAdd.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonAddPhotoAddProduct ->
            {
                imgPresenter.imageSearch(this, PiCK_IMAGE_REQUEST)
            }

            R.id.buttonFinishAddProduct ->
            {
                if (name.text == null || name.text.toString() == "")
                {
                    Toast.makeText(this,R.string.NoNameWarning,Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }

                var radioText = String()
                val id: Int = radioGroup.checkedRadioButtonId
                if (id!=-1)
                {
                    val radio: RadioButton = findViewById(id)
                    radioText = radio.text.toString()
                }else{
                    Toast.makeText(applicationContext,R.string.NoMeasureWarning,Toast.LENGTH_SHORT).show()
                    return@OnClickListener
                }

                if (addProductResultUri == null)
                {
                    Toast.makeText(this,R.string.NoImageWarning,Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }

                mediaName = imgPresenter.uploadProductImageToDb(addProductResultUri!!,this)
                addProductPresenter.createProduct(name.text.toString(), mediaName,radioText, currentUser.userId
                        ,this,resources.getString(R.string.NameTakenWarning))
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PiCK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            addProductResultUri = data.data
            img.setImageURI(addProductResultUri)
        }
    }

}