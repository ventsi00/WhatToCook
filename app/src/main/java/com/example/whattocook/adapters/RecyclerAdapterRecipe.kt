package com.example.whattocook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.R
import com.example.whattocook.RecipeActivity
import com.example.whattocook.models.Recipe
import com.example.whattocook.presenters.RecipePresenter
import com.example.whattocook.presenters.UiPresenter

class RecyclerAdapterRecipe(private val itemList:MutableList<Recipe>,private val context: Context): RecyclerView.Adapter<RecyclerAdapterRecipe.MyViewHolderRecipe>() {

    private val uiPresenter = UiPresenter()
    private val recipePresenter = RecipePresenter()

    class MyViewHolderRecipe(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout: ConstraintLayout = itemView.findViewById(R.id.productsRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProductsRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewProductsRecycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderRecipe {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_recycler_item,parent,false)
        return MyViewHolderRecipe(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderRecipe, position: Int) {
        val currentItem = itemList[position]
        holder.textView.text = currentItem.name
        recipePresenter.getRecipeImage(currentItem.image,holder.imageView)
        holder.layout.setOnClickListener{
            uiPresenter.changeUi(context,RecipeActivity(),currentItem.name,currentItem.image)
        }
    }

    override fun getItemCount() = itemList.size
}

