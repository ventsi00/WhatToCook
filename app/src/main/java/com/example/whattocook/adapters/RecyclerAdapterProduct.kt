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
import com.example.whattocook.*
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.ChooseProductPresenter

private val cpPresenter = ChooseProductPresenter()
class RecyclerAdapterHome(private val itemList:MutableList<Product>,private val context:Context): RecyclerView.Adapter<RecyclerAdapterHome.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout:ConstraintLayout = itemView.findViewById(R.id.homeRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewHomeRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewHomeRecycler)
        val textViewQuantity: TextView = itemView.findViewById(R.id.textViewChosenQuantityHome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]

        cpPresenter.getProductImage(currentItem.image, holder.imageView)
        holder.textView.text = currentItem.name
        holder.textViewQuantity.text = currentItem.productMeasure + ": " + currentItem.currentMeasure.toString()

        holder.layout.setOnLongClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.resources.getString(R.string.Alert))
            builder.setMessage(context.resources.getString(R.string.DeleteAlert))

            builder.setPositiveButton(context.resources.getString(R.string.Ok)) { _, _ ->
                itemList.removeAt(position)
                notifyDataSetChanged()
            }

            builder.show()
            true
        }


    }

    override fun getItemCount() = itemList.size

}