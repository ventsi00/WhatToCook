package com.example.whattocook.repository

import android.app.Dialog
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whattocook.adapters.*
import com.example.whattocook.models.*
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import java.util.*


class DataBase {
    private var database = FirebaseDatabase.getInstance()
    private var dbRoot = database.reference
    private var dbUsers = dbRoot.child("Users")
    private var dbProducts = dbRoot.child("Products")
    private var dbAppliances = dbRoot.child("Appliances")
    private var dbRecipes = dbRoot.child("Recipes")

    fun getName(id: String, text: TextView)
    {
        dbUsers.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                text.text = snapshot.child("Username").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Error getting name")
            }

        })
    }

    fun getAvatar(child:String,storage:StorageReference,view: ImageView)
    {
        var storageRef:StorageReference = storage.child(child)
        if (child == "")
        {
            storageRef = storage.child("avatar.png")
        }
       storageRef.getBytes(1024 * 1024)
            .addOnSuccessListener { bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                view.setImageBitmap(bitmap)
            }
    }

    fun getAvatarInstantly(id:String,storage: StorageReference,view: ImageView,dialog:Dialog)
    {
        dbUsers.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               getAvatar(snapshot.child("Avatar").value.toString(),storage,view)
                Thread.sleep(1000)
                dialog.hide()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Error getting img")
            }

        })
    }

    fun getImage(name:String,storage: StorageReference,view: ImageView)
    {
        val storageRef: StorageReference = storage.child(name)
        storageRef.getBytes(1024 * 1024)
                .addOnSuccessListener { bytes ->
                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    view.setImageBitmap(bitmap)
                }
    }

    private fun updateUserAvatar(user: User, avatar: String)
    {
        user.avatar = avatar
    }

    fun updateAvatar(id: String, avatar: String, user: User)
    {
        updateUserAvatar(user, avatar)
        dbUsers.child(id).child("Avatar").setValue(avatar)
    }

    fun getUser(id: String, user: User)
    {
        dbUsers.child(id).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user.username = snapshot.child("Username").value.toString()
                user.userId = id
                if (snapshot.child("Avatar").value != null)
                {
                    user.avatar = snapshot.child("Avatar").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Error getting name")
            }

        })
    }


    fun createUser(userID: String, name: String)
    {
        dbUsers.child(userID).child("Username").setValue(name)
        dbUsers.child(userID).child("Appliances").child("Null").setValue("default")
        dbUsers.child(userID).child("Avatar").setValue("avatar.png")

    }

    fun createProduct(name: String,media:String,measure:String,userId:String,activity:AppCompatActivity,message:String)
    {
        var result = true
        dbProducts.addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.key == name)
                    {
                        result = false
                    }
                }

                if (result)
                {
                    dbProducts.child(name).child("Media").setValue(media)
                    dbProducts.child(name).child("Measure").setValue(measure)
                    dbProducts.child(name).child("Creator").setValue(userId)
                    activity.finish()
                }
                else
                {
                    Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
                }
                return
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Error getting name")
            }

        })
    }

    fun createAppliance(name: String,media:String,userId:String,activity:AppCompatActivity,message:String)
    {
        var result = true
        dbAppliances.addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.key == name)
                    {
                        result = false
                    }
                }

                if (result)
                {
                    dbAppliances.child(name).child("Media").setValue(media)
                    dbAppliances.child(name).child("Creator").setValue(userId)
                    activity.finish()
                }
                else
                {
                    Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
                }
                return
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Error getting name")
            }

        })
    }

    fun startRecyclerWithAllProducts(recyclerView: RecyclerView,context:AppCompatActivity,list: MutableList<Product>,targetRecycler:RecyclerView)
    {
        val productList = mutableListOf<Product>()
        dbProducts.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    val product = Product()
                    product.name = item.key.toString()
                    product.image = item.child("Media").value.toString()
                    product.productCreator = item.child("Creator").value.toString()
                    product.productMeasure = item.child("Measure").value.toString()
                    productList.add(product)
                }
                recyclerView.adapter = RecyclerAdapterChooseProduct(productList,context,list,targetRecycler)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    fun startRecyclerWithUserAppliances(recyclerView: RecyclerView,context:AppCompatActivity,id:String,applianceList:MutableList<Appliance>)
    {
        dbUsers.child(id).child("Appliances").addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.key != "Null")
                    {
                        val appliance = Appliance()
                        appliance.name = item.key.toString()
                        appliance.image = item.child("Media").value.toString()
                        appliance.creator = item.child("Creator").value.toString()
                        applianceList.add(appliance)
                    }

                }
                recyclerView.adapter = RecyclerAdapterProfile(applianceList,context)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun addAppliance(appliance: String,id:String)
    {
        dbUsers.child(id).child("Appliances").child(appliance).setValue(1)
    }

    fun removeAppliance(appliance: String,id: String)
    {
        dbUsers.child(id).child("Appliances").child(appliance).removeValue()
    }

    fun deleteAppliance(appliance: String,context: Context,message:String,itemList:MutableList<ModelAny>,position:Int,storage: StorageReference)
    {
        var checker = false
        dbRecipes.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    for (appliances in item.child("Appliances").children)
                    {
                        if (appliances.key == appliance)
                        {
                            checker = true
                        }
                    }
                }
                dbUsers.addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for(app in snapshot.child("Appliances").children)
                        {
                            if (app.key == appliance)
                            {
                                checker = true
                            }
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
                if (!checker)
                {
                    dbAppliances.child(appliance).removeValue()
                    storage.delete()
                    itemList.removeAt(position)
                }
                else
                {
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteProduct(product:String,context: Context,message: String,itemList:MutableList<ModelAny>,position:Int,storage: StorageReference)
    {
        var checker = false
        dbRecipes.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    for (products in item.child("Products").children)
                    {
                        if (products.key == product)
                        {
                            checker = true
                        }
                    }
                }
                if (!checker)
                {
                    dbProducts.child(product).removeValue()
                   storage.delete()
                    itemList.removeAt(position)
                }
                else
                {
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun deleteRecipe(recipe:String,itemList:MutableList<ModelAny>,position:Int,storage: StorageReference)
    {
        dbRecipes.child(recipe).removeValue()
        storage.delete()
        itemList.removeAt(position)

    }


    fun startRecyclerWithSomeProducts(recyclerView: RecyclerView,inputString:String,context:AppCompatActivity,list: MutableList<Product>,targetRecycler:RecyclerView)
    {
        val productList = mutableListOf<Product>()
        dbProducts.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    val product = Product()
                    product.name = item.key.toString().toLowerCase(Locale(item.key.toString()))
                    product.image = item.child("Media").value.toString()
                    product.productCreator = item.child("Creator").value.toString()
                    product.productMeasure = item.child("Measure").value.toString()
                    productList.add(product)
                }
                val sortedProductList = productList.filter { it.name.contains(inputString.toLowerCase(Locale(inputString))) }
                recyclerView.adapter = RecyclerAdapterChooseProduct(sortedProductList.toMutableList(),context,list,targetRecycler)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun startRecyclerWithAllAppliances(recyclerView: RecyclerView,context:AppCompatActivity,list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser:Boolean)
    {
        val applianceList = mutableListOf<Appliance>()
        dbAppliances.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    val appliance = Appliance()
                    appliance.name = item.key.toString()
                    appliance.image = item.child("Media").value.toString()
                    appliance.creator = item.child("Creator").value.toString()
                    applianceList.add(appliance)
                }
                recyclerView.adapter = RecyclerAdapterChooseAppliance(applianceList,context,list,targetRecycler,addToUser)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun startRecyclerWithSomeAppliances(recyclerView: RecyclerView,inputString:String,context:AppCompatActivity,list: MutableList<Appliance>,targetRecycler:RecyclerView,addToUser: Boolean)
    {
        val applianceList = mutableListOf<Appliance>()
        dbAppliances.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    val appliance = Appliance()
                    appliance.name = item.key.toString().toLowerCase(Locale(item.key.toString()))
                    appliance.image = item.child("Media").value.toString()
                    appliance.creator = item.child("Creator").value.toString()
                    applianceList.add(appliance)
                }
                val sortedApplianceList = applianceList.filter { it.name.contains(inputString.toLowerCase(Locale(inputString))) }
                recyclerView.adapter = RecyclerAdapterChooseAppliance(sortedApplianceList.toMutableList(),context,list,targetRecycler,addToUser)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun getApplianceImageProfile(name:String,applianceStorageRef:StorageReference,view:ImageView)
    {
     dbAppliances.child(name).child("Media").addListenerForSingleValueEvent(object:ValueEventListener{
         override fun onDataChange(snapshot: DataSnapshot) {

             val storageRef: StorageReference = applianceStorageRef.child(snapshot.value.toString())
            storageRef.getBytes(1024 * 1024)
                    .addOnSuccessListener { bytes ->
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        view.setImageBitmap(bitmap)
                    }
         }

         override fun onCancelled(error: DatabaseError) {

         }

     })
    }

    fun createRecipe(name:String,media:String,description:String,appliances:MutableList<Appliance>,products:MutableList<Product>,context:Context,message: String,id: String)
    {
        var result = true
        dbRecipes.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.key == name)
                    {
                        result = false
                    }
                }

                if(result)
                {
                    val recipe = dbRecipes.child(name)
                    recipe.child("Media").setValue(media)
                    recipe.child("Description").setValue(description)
                    recipe.child("Creator").setValue(id)
                    val recipeProducts = recipe.child("Products")
                    val recipeAppliances = recipe.child("Appliances")

                    for (app in appliances)
                    {
                        recipeAppliances.child(app.name).setValue("")
                    }
                    for (pro in products)
                    {
                        recipeProducts.child(pro.name).setValue(pro.currentMeasure)
                    }
                }
                else
                {
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun startRecyclerWithRecipes(recycler:RecyclerView,productList:MutableList<Product>,username:String,context: Context)
    {
        val resultList = mutableListOf<Recipe>()
        val applianceListNew = mutableListOf<Appliance>()

        dbUsers.child(username).child("Appliances").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if(item.key == "Null")
                    {
                        continue
                    }
                    val addAppliance = Appliance()
                    addAppliance.name = item.key!!

                    applianceListNew.add(addAppliance)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        dbRecipes.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (recipe in snapshot.children)
                {
                    var suitableProduct = false
                    var suitableAppliance = false
                    val recipeAppliances = mutableListOf<Appliance>()
                    val recipeProducts = mutableListOf<Product>()
                    val productListNew = mutableListOf<Product>()


                    for (item in recipe.child("Products").children)
                    {
                        val addProduct = Product()
                        addProduct.name = item.key!!
                        addProduct.currentMeasure = item.value.toString().toInt()

                        recipeProducts.add(addProduct)
                    }

                    for (item in recipe.child("Appliances").children)
                    {
                        val addAppliance = Appliance()
                        addAppliance.name = item.key!!

                        recipeAppliances.add(addAppliance)
                    }

                    for (item in productList)
                    {
                        val addProduct = Product()
                        addProduct.name = item.name
                        addProduct.currentMeasure = item.currentMeasure

                        productListNew.add(addProduct)
                    }

                    var productCheck = 0
                    for(item in recipeProducts)
                    {
                        if (productList.any{ it.name.equals(item.name, ignoreCase = true) && it.currentMeasure >= item.currentMeasure})
                        {
                            productCheck++
                        }
                        if(productCheck == recipeProducts.size)
                        {
                            suitableProduct = true
                        }
                    }

                    if (applianceListNew.containsAll(recipeAppliances))
                    {
                        suitableAppliance = true
                    }

                    if (suitableProduct && suitableAppliance)
                    {
                       val addRecipe = Recipe()
                        addRecipe.name = recipe.key!!
                        addRecipe.image = recipe.child("Media").value.toString()
                        resultList.add(addRecipe)
                    }
                }
                recycler.adapter = RecyclerAdapterRecipe(resultList,context)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getAddedProducts(recyclerView: RecyclerView,context:Context,id:String)
    {
        val productList = mutableListOf<ModelAny>()
        dbProducts.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.child("Creator").value==id) {
                        val product = ModelAny()
                        product.name = item.key.toString()
                        product.image = item.child("Media").value.toString()
                        productList.add(product)
                    }
                }
                recyclerView.layoutManager = GridLayoutManager(context,2)
                recyclerView.adapter = RecyclerAdapterAdditions(productList,context,"product")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun getAddedAppliances(recyclerView: RecyclerView,context:Context,id:String)
    {
        val applianceList = mutableListOf<ModelAny>()
        dbAppliances.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.child("Creator").value == id)
                    {
                        val appliance = ModelAny()
                        appliance.name = item.key.toString()
                        appliance.image = item.child("Media").value.toString()
                        applianceList.add(appliance)
                    }
                }
                recyclerView.layoutManager = GridLayoutManager(context,2)
                recyclerView.adapter = RecyclerAdapterAdditions(applianceList,context,"appliance")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun getAddedRecipes(recyclerView: RecyclerView,context:Context,id:String)
    {
        val recipeList = mutableListOf<ModelAny>()
        dbRecipes.addListenerForSingleValueEvent(object: ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children)
                {
                    if (item.child("Creator").value == id)
                    {
                        val recipe = ModelAny()
                        recipe.name = item.key.toString()
                        recipe.image = item.child("Media").value.toString()
                        recipeList.add(recipe)
                    }
                }
                recyclerView.layoutManager = GridLayoutManager(context,2)
                recyclerView.adapter = RecyclerAdapterAdditions(recipeList,context,"recipe")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadRecipe(name:String,image:String,nameText:TextView,description:TextView,products:ListView,appliances:ListView,context: Context,storage: StorageReference,imageView: ImageView)
    {
        getImage(image,storage,imageView)
        val listProducts = mutableListOf<String>()
        val listAppliances = mutableListOf<String>()

        dbRecipes.child(name).addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                nameText.text = name
                description.text = snapshot.child("Description").value.toString()

                for (item in snapshot.child("Products").children)
                {
                    val addProduct =item.key!! +": "+ item.value
                    listProducts.add(addProduct)
                }

                for (item in snapshot.child("Appliances").children)
                {
                    val addAppliance =item.key!!
                    listAppliances.add(addAppliance)
                }

                val adapterProducts: ArrayAdapter<String> = ArrayAdapter(context,android.R.layout.simple_list_item_1,listProducts)
                val adapterAppliances: ArrayAdapter<String> = ArrayAdapter(context,android.R.layout.simple_list_item_1,listAppliances)
                products.adapter = adapterProducts
                appliances.adapter = adapterAppliances

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}