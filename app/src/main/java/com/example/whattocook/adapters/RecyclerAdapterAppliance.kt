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
import com.example.whattocook.currentUser
import com.example.whattocook.models.Appliance
import com.example.whattocook.presenters.ChooseAppliancePresenter


private val caPresenter = ChooseAppliancePresenter()

class RecyclerAdapterProfile(private val itemList:MutableList<Appliance>,private val context: Context): RecyclerView.Adapter<RecyclerAdapterProfile.MyViewHolderProfile>() {

    class MyViewHolderProfile(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout: ConstraintLayout = itemView.findViewById(R.id.productsRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProductsRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewProductsRecycler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderProfile {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_recycler_item,parent,false)
        return MyViewHolderProfile(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderProfile, position: Int) {
        val currentItem = itemList[position]

        caPresenter.getApplianceImageProfile(currentItem.name, holder.imageView)
        holder.textView.text = currentItem.name

        holder.layout.setOnLongClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.resources.getString(R.string.Alert))
            builder.setMessage(context.resources.getString(R.string.DeleteAlert))

            builder.setPositiveButton(context.resources.getString(R.string.Ok)) { _, _ ->
                itemList.removeAt(position)
                notifyDataSetChanged()
                caPresenter.removeAppliance(currentItem.name, currentUser.userId)
            }

            builder.show()
            true
        }


    }

    override fun getItemCount() = itemList.size

}