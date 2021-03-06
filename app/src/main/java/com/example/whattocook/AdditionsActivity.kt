package com.example.whattocook

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.whattocook.fragments.FragmentMyAppliances
import com.example.whattocook.fragments.FragmentMyProducts
import com.example.whattocook.fragments.FragmentMyRecipes


private lateinit var btnProducts:Button
private lateinit var btnAppliances:Button
private lateinit var btnRecipes:Button

class AdditionsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.additions)

        btnProducts = findViewById(R.id.buttonMyProducts)
        btnAppliances = findViewById(R.id.buttonMyAppliances)
        btnRecipes = findViewById(R.id.buttonMyRecipes)

        btnProducts.setOnClickListener(clickListener)
        btnAppliances.setOnClickListener(clickListener)
        btnRecipes.setOnClickListener(clickListener)

        val fragment = FragmentMyProducts()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.constraintLayoutFragmentHolder,fragment)
        transaction.commit()
    }
    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.buttonMyProducts -> {
                val fragment = FragmentMyProducts()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.constraintLayoutFragmentHolder,fragment)
                transaction.commit()
            }

            R.id.buttonMyAppliances -> {
                val fragment = FragmentMyAppliances()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.constraintLayoutFragmentHolder,fragment)
                transaction.commit()
            }

            R.id.buttonMyRecipes -> {
                val fragment = FragmentMyRecipes()
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.constraintLayoutFragmentHolder,fragment)
                transaction.commit()
            }
        }
    }
}