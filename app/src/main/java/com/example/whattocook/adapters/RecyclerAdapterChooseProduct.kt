package com.example.whattocook.adapters

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.R
import com.example.whattocook.currentUser
import com.example.whattocook.models.Product
import com.example.whattocook.presenters.ChooseProductPresenter

private val cpPresenter = ChooseProductPresenter()
class RecyclerAdapterChooseProduct(private val cpList: MutableList<Product>,private val activityContext: AppCompatActivity
,private var list:MutableList<Product>,private var recycler:RecyclerView):RecyclerView.Adapter<RecyclerAdapterChooseProduct.MyViewHolderCP>() {

    class MyViewHolderCP(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val layout:ConstraintLayout = itemView.findViewById(R.id.productsRecyclerItemLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewProductsRecycler)
        val textView: TextView = itemView.findViewById(R.id.textViewProductsRecycler)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MyViewHolderCP
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_recycler_item, parent, false)
        return MyViewHolderCP(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderCP, position: Int)
    {
        val currentItem = cpList[position]

        holder.textView.text = currentItem.name
        cpPresenter.getProductImage(currentItem.image, holder.imageView)

        holder.layout.setOnClickListener{
            val dialog = Dialog(activityContext)
            dialog.setContentView(R.layout.choose_measure)
            dialog.show()
            val textView = dialog.findViewById<TextView>(R.id.textViewChooseMeasure)
            textView.text = currentItem.productMeasure
            val numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPickerChooseMeasure)
            numberPicker.minValue = 0
            numberPicker.maxValue = 1000
            val button = dialog.findViewById<Button>(R.id.buttonChooseMeasure)
            button.setOnClickListener{
              currentItem.currentMeasure = numberPicker.value

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
                    dialog.dismiss()
                    activityContext.onBackPressed()
                }
                else
                {
                    Toast.makeText(activityContext,activityContext.resources.getString(R.string.AlreadyAdded),Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun getItemCount() = cpList.size
}
