package com.example.whattocook.models

data class Recipe(var products:MutableList<Product> = mutableListOf()
                  , var appliances:MutableList<Appliance> = mutableListOf()
                  , var description:String = ""
                  ,var creator:String = ""):ModelAny()
