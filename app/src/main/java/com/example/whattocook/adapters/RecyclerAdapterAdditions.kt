package com.example.whattocook.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.R
import com.example.whattocook.models.ModelAny
import com.example.whattocook.presenters.AdditionPresenter
import com.example.whattocook.presenters.ChooseAppliancePresenter
import com.example.whattocook.presenters.ChooseProductPresenter
import com.example.whattocook.presenters.RecipePresenter

private val caPresenter = ChooseAppliancePresenter()
private val cpPresenter = ChooseProductPresenter()
private val recipePresenter = RecipePresenter()
private val additionPresenter = AdditionPresenter()

class RecyclerAdapterAdditions(private val itemList:MutableList<ModelAny>, private val context: Context,private val type:String): RecyclerView.Adapter<RecyclerAdapterAdditions.MyViewHolderAdditions>() {

    class MyViewHolderAdditions(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout: ConstraintLayout = itemView.findViewById(R.id.productsRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProductsRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewProductsRecycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderAdditions {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_recycler_item,parent,false)
        return RecyclerAdapterAdditions.MyViewHolderAdditions(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderAdditions, position: Int) {
        val currentItem = itemList[position]

        when (type) {
            "product" ->   cpPresenter.getProductImage(currentItem.image, holder.imageView)
            "appliance" ->  caPresenter.getApplianceImageProfile(currentItem.name, holder.imageView)
            "recipe" -> recipePresenter.getRecipeImage(currentItem.image,holder.imageView)
        }

        caPresenter.getApplianceImageProfile(currentItem.name, holder.imageView)
        holder.textView.text = currentItem.name

        holder.layout.setOnLongClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.resources.getString(R.string.Alert))
            builder.setMessage(context.resources.getString(R.string.DeleteAlertPermanent))

            builder.setPositiveButton(context.resources.getString(R.string.Ok)) { _, _ ->
                when (type) {
                    "product" ->  additionPresenter.deleteProduct(currentItem.name,currentItem.image,context,context.getString(R.string.CantDeleteWarning),itemList,position)
                    "appliance" ->  additionPresenter.deleteAppliance(currentItem.name,currentItem.image,context,context.getString(R.string.CantDeleteWarning),itemList,position)
                    "recipe" ->  additionPresenter.deleteRecipe(currentItem.name,currentItem.image,itemList, position)
                }
                notifyDataSetChanged()
            }

            builder.show()
            true
        }
    }

    override fun getItemCount() = itemList.count()
}