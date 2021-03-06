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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.adapters.RecyclerAdapterHome
import com.example.whattocook.adapters.RecyclerAdapterProfile
import com.example.whattocook.models.Appliance
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.RecipePresenter
import com.example.whattocook.presenters.ImagePresenter
import com.example.whattocook.presenters.UiPresenter


var listProductsCreateRecipe = mutableListOf<Product>()
var listAppliancesCreateRecipe = mutableListOf<Appliance>()
lateinit var recyclerViewProductsCreateRecipe: RecyclerView
lateinit var recyclerViewAppliancesCreateRecipe: RecyclerView

private const val PiCK_IMAGE_REQUEST = 1
private var resultUri: Uri? = null

private val imagePresenter = ImagePresenter()
private val crPresenter = RecipePresenter()
private val uiPresenter = UiPresenter()

private lateinit var addPhotoButton: Button
private lateinit var addProductButton:Button
private lateinit var addApplianceButton: Button
private lateinit var finishButton:Button
private lateinit var imageView:ImageView
private lateinit var name:EditText
private lateinit var description:EditText

class CreateRecipeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_recipe)

        listProductsCreateRecipe.clear()
        listAppliancesCreateRecipe.clear()

        addPhotoButton = findViewById(R.id.buttonAddPhotoCreateRecipe)
        addProductButton = findViewById(R.id.buttonAddProductCreateRecipe)
        addApplianceButton = findViewById(R.id.buttonAddApplianceCreateRecipe)
        finishButton = findViewById(R.id.buttonFinishCreateRecipe)
        name = findViewById(R.id.editTextTextNameCreateRecipe)
        imageView = findViewById(R.id.imageViewCreateRecipe)
        recyclerViewProductsCreateRecipe = findViewById(R.id.recyclerViewProductsCreateRecipe)
        recyclerViewAppliancesCreateRecipe = findViewById(R.id.recyclerViewAppliancesCreateRecipe)
        description = findViewById(R.id.textViewDescriptionCreateRecipe)

        addPhotoButton.setOnClickListener(clickListener)
        addProductButton.setOnClickListener(clickListener)
        addApplianceButton.setOnClickListener(clickListener)
        finishButton.setOnClickListener(clickListener)

        recyclerViewProductsCreateRecipe.adapter = RecyclerAdapterHome(listProductsCreateRecipe,this)
        recyclerViewProductsCreateRecipe.layoutManager = GridLayoutManager(this,1)

        recyclerViewAppliancesCreateRecipe.adapter = RecyclerAdapterProfile(listAppliancesCreateRecipe,this)
        recyclerViewAppliancesCreateRecipe.layoutManager = GridLayoutManager(this, 1)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonAddPhotoCreateRecipe ->
            {
                imagePresenter.imageSearch(this, PiCK_IMAGE_REQUEST)
            }

            R.id.buttonAddProductCreateRecipe ->
            {
                uiPresenter.changeUi(this,ChooseProductActivity(),"createRecipe")
            }

            R.id.buttonAddApplianceCreateRecipe ->
            {
                uiPresenter.changeUi(this,ChooseApplianceActivity(),"createRecipe")
            }

            R.id.buttonFinishCreateRecipe ->
            {
                if(resultUri == null)
                {
                    Toast.makeText(this,R.string.NoImageWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else if(name.text == null || name.text.toString() == "")
                {
                    Toast.makeText(this,R.string.NoNameWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else if(listProductsCreateRecipe.size == 0)
                {
                    Toast.makeText(this,R.string.NoProductsWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else if(listAppliancesCreateRecipe.size == 0)
                {
                    Toast.makeText(this,R.string.NoAppliancesWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else if (description.text == null || description.text.toString() == "")
                {
                    Toast.makeText(this,R.string.NoDescriptionWarning, Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else
                {
                    val imageName = imagePresenter.uploadRecipeImageToDb(resultUri!!,this)
                    crPresenter.createRecipe(name.text.toString(),imageName, description.text.toString()
                            , listProductsCreateRecipe, listAppliancesCreateRecipe,
                            this,resources.getString(R.string.NameTakenWarning))
                    onBackPressed()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PiCK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            resultUri = data.data
            imageView.setImageURI(resultUri)
        }
    }

}