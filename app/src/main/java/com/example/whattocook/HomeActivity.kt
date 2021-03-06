package com.example.whattocook

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.adapters.RecyclerAdapterHome
import com.example.whattocook.models.Product
import com.example.whattocook.models.User
import com.example.whattocook.presenters.UiPresenter
import com.example.whattocook.presenters.UserPresenter

var currentUser: User = User()
lateinit var pic:ImageView
lateinit var recyclerViewHome:RecyclerView
var productList = mutableListOf<Product>()

private var userPresenter = UserPresenter()
private val uiPresenter: UiPresenter = UiPresenter()

private lateinit var name:TextView
private lateinit var buttSearch:Button
private lateinit var buttAdd:Button
private lateinit var topSection:ConstraintLayout

private var backPressCounter = 0




class HomeActivity: AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val splashView = layoutInflater.inflate(R.layout.splashscreen,null)
        val splashDialog = Dialog(this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        splashDialog.setCancelable(false)
        splashDialog.setContentView(splashView)
        splashDialog.show()



        UID = intent.getStringExtra("UID")!!
        userPresenter.getUser(UID, currentUser)

        name = findViewById(R.id.textViewHomeName)
        pic = findViewById(R.id.imageViewHome)
        buttSearch = findViewById(R.id.ButtonSearchHome)
        buttSearch.setOnClickListener(clickListener)
        buttAdd = findViewById(R.id.ButtonAddHome)
        buttAdd.setOnClickListener(clickListener)
        recyclerViewHome = findViewById(R.id.recyclerViewHome)
        topSection = findViewById(R.id.constraintLayoutHomeTop)
        topSection.setOnClickListener(clickListener)

        userPresenter.getName(UID, name)
        userPresenter.getAvatarInstantly(UID, pic,splashDialog)




        recyclerViewHome.adapter = RecyclerAdapterHome(productList,this)
        recyclerViewHome.layoutManager = GridLayoutManager(this, 2)

    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.ButtonSearchHome ->
            {
             uiPresenter.changeUi(this,RecipeResultsActivity())
            }
            R.id.ButtonAddHome -> {
                uiPresenter.changeUi(this,ChooseProductActivity(), "home")
            }
            R.id.constraintLayoutHomeTop -> {
                uiPresenter.changeUi(this,ProfileActivity())
            }
        }
    }

    override fun onBackPressed() {
        backPressCounter++
        if (backPressCounter == 1)
        {
            Toast.makeText(this,R.string.LeaveWarning, Toast.LENGTH_LONG).show()
        }
        else
        {
            backPressCounter = 0
            finishAffinity()
        }
    }
}