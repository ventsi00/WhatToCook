package com.example.whattocook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.*
import com.example.whattocook.models.Appliance
import com.example.whattocook.presenters.ChooseAppliancePresenter

private val caPresenter = ChooseAppliancePresenter()

class RecyclerAdapterChooseAppliance(private val cpList: MutableList<Appliance>,private val activityContext: AppCompatActivity
,private var list:MutableList<Appliance>,private var recycler:RecyclerView,private val addToUser:Boolean):RecyclerView.Adapter<RecyclerAdapterChooseAppliance.MyViewHolderCA>()  {

    class MyViewHolderCA(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout: ConstraintLayout = itemView.findViewById(R.id.productsRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProductsRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewProductsRecycler)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolderCA
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_recycler_item, parent, false)
        return MyViewHolderCA(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderCA, position: Int)
    {
        val currentItem = cpList[position]

        holder.textView.text = currentItem.name
        caPresenter.getApplianceImage(currentItem.image, holder.imageView)

        holder.layout.setOnClickListener{

            var old = false
                for (item in list)
                {
                    if (item.name == currentItem.name)
                    {
                        old = true
                    }
                }

                if (!old)
                {
                    list.add(currentItem)
                    recycler.adapter!!.notifyDataSetChanged()

                    if(addToUser)
                    {
                        caPresenter.addAppliance(currentItem.name, currentUser.userId)
                    }

                    activityContext.onBackPressed()

                }
                else
                {
                 Toast.makeText(activityContext,activityContext.resources.getString(R.string.AlreadyAdded),Toast.LENGTH_LONG).show()
                }



        }
    }

    override fun getItemCount() = cpList.size

}