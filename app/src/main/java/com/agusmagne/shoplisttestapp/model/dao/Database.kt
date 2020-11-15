package com.agusmagne.shoplisttestapp.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.agusmagne.shoplisttestapp.model.entities.Item

@Database(entities = [Item::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}