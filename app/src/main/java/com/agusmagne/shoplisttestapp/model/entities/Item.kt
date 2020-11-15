package com.agusmagne.shoplisttestapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Item(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @SerializedName("item_name") @ColumnInfo(name = "NAME") var name: String = "",
    @ColumnInfo(name = "AMOUNT") var amount: Int = 0,
    var available: String = UNAVAILABLE
) {
    companion object {
        const val AVAILABLE = "yes"
        const val UNAVAILABLE = "no"
    }
}