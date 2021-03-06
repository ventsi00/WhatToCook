package com.example.whattocook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.models.Appliance
import com.example.whattocook.presenters.ImagePresenter
import com.example.whattocook.presenters.ProfilePresenter
import com.example.whattocook.presenters.UiPresenter
import com.google.firebase.auth.FirebaseAuth


var applianceList = mutableListOf<Appliance>()
lateinit var recyclerProfile:RecyclerView

///////////for image choosing///////////
private const val PiCK_IMAGE_REQUEST = 1
private var resultUri: Uri? = null
/////////////////////////////

private var imgTamper:ImagePresenter = ImagePresenter()
private var uiPresenter = UiPresenter()

private lateinit var name:TextView
private lateinit var avatar:ImageView
private lateinit var btnChangePhoto:Button
private lateinit var btnLogOut:Button
private lateinit var btnAddProduct:Button
private lateinit var btnCreateAppliance:Button
private lateinit var btnCreateRecipe:Button
private lateinit var btnAddAppliance:Button
private lateinit var btnMyAdditions:Button
private val profilePresenter = ProfilePresenter()


class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        recyclerProfile = findViewById(R.id.recyclerProfile)

        applianceList.clear()
        profilePresenter.startRecyclerWithUserAppliances(recyclerProfile,this, currentUser.userId, applianceList)
        recyclerProfile.layoutManager = GridLayoutManager(this, 2)

        name = findViewById(R.id.textViewNameProfile)
        avatar = findViewById(R.id.imageViewProfile)
        btnChangePhoto = findViewById(R.id.buttonChangePicProfile)
        btnLogOut = findViewById(R.id.buttonLogOut)
        btnAddProduct = findViewById(R.id.buttonAddProductProfile)
        btnCreateAppliance = findViewById(R.id.buttonCreateApplianceProfile)
        btnAddAppliance = findViewById(R.id.buttonAddApplianceProfile)
        btnCreateRecipe = findViewById(R.id.buttonAddRecipeProfile)
        btnMyAdditions = findViewById(R.id.buttonMyAdditions)

        imgTamper.getImage(currentUser.avatar, avatar)
        name.text = currentUser.username
        btnChangePhoto.setOnClickListener(clickListener)
        btnLogOut.setOnClickListener(clickListener)
        btnAddProduct.setOnClickListener(clickListener)
        btnCreateAppliance.setOnClickListener(clickListener)
        btnAddAppliance.setOnClickListener(clickListener)
        btnCreateRecipe.setOnClickListener(clickListener)
        btnMyAdditions.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonMyAdditions ->
            {
                uiPresenter.changeUi(this,AdditionsActivity())
            }

            R.id.buttonAddProductProfile ->
            {
                uiPresenter.changeUi(this,AddProductActivity())
            }

            R.id.buttonChangePicProfile ->
            {
                imgTamper.imageSearch(this, PiCK_IMAGE_REQUEST)
            }

            R.id.buttonLogOut ->
            {
                FirebaseAuth.getInstance().signOut()
                val uiPresenter = UiPresenter()
                uiPresenter.changeUi(this,MainActivity())
                productList.clear()
            }

            R.id.buttonCreateApplianceProfile ->
            {
                uiPresenter.changeUi(this,CreateApplianceActivity())
            }

            R.id.buttonAddApplianceProfile ->
            {
                uiPresenter.changeUi(this,ChooseApplianceActivity(),"profile")
            }

            R.id.buttonAddRecipeProfile ->
            {
                uiPresenter.changeUi(this,CreateRecipeActivity())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PiCK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            resultUri = data.data
            avatar.setImageURI(resultUri)
            pic.setImageURI(resultUri)
           val avatarName = imgTamper.uploadToDb(resultUri!!,this, currentUser)
            imgTamper.updateAvatar(currentUser.userId,avatarName,currentUser)
        }
    }

}