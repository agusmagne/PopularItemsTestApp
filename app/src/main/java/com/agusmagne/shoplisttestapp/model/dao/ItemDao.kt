package com.agusmagne.shoplisttestapp.model.dao

import androidx.room.*
import com.agusmagne.shoplisttestapp.model.entities.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM item")
    fun getAllItems(): List<Item>

    @Delete(entity = Item::class)
    fun deleteItem(item: Item)

    @Insert
    fun insertAllItems(vararg item: Item): Array<Long>

    @Update(entity = Item::class)
    fun updateItem(item: Item)

}