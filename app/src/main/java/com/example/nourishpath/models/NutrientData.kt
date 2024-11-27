package com.example.nourishpath.models

import android.os.Parcel
import android.os.Parcelable

data class NutrientData(
    val totalNutrients: LinkedHashMap<String, Float>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readHashMap(Float::class.java.classLoader) as LinkedHashMap<String, Float>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeMap(totalNutrients)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NutrientData> {
        override fun createFromParcel(parcel: Parcel): NutrientData {
            return NutrientData(parcel)
        }

        override fun newArray(size: Int): Array<NutrientData?> {
            return arrayOfNulls(size)
        }
    }
}