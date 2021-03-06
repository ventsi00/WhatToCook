package com.example.whattocook.models

import android.os.Parcel
import android.os.Parcelable

data class Product( var productCreator: String="", var productMeasure:String = "", var currentMeasure:Int = 0): ModelAny()