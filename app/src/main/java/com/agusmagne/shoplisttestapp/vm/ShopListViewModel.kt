package com.agusmagne.shoplisttestapp.vm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agusmagne.shoplisttestapp.model.dao.Database
import com.agusmagne.shoplisttestapp.model.entities.Item
import com.agusmagne.shoplisttestapp.network.PopularItemsEndpoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class ShopListViewModel @Inject constructor(
    private val application: Application,
    private val database: Database
) : ViewModel() {

    @Inject
    lateinit var popularItemsEndpoints: PopularItemsEndpoints

    val popularItems = MutableLiveData<List<String>>()
    val shoppingCartList = MutableLiveData<MutableList<Item>>(mutableListOf())

    private val TAG = "ShopListViewModel"
    private var allCartList = mutableListOf<Item>()

    init {
        GlobalScope.launch(Dispatchers.IO) {
            allCartList = database.itemDao().getAllItems().toMutableList()
            shoppingCartList.postValue(allCartList)
        }
    }

    fun fetchPopularItems() {
        popularItemsEndpoints.getPopularItems().enqueue(callback)
    }

    fun addItem(name: String, amount: Int) {
        val index = allCartList.indexOfFirst { item -> item.name == name }
        if (index == -1) {
            addNewItem(name, amount)
        } else {
            updateItem(index, amount)
        }
        shoppingCartList.value = allCartList
    }


    fun deleteItem(position: Int) {
        val list = shoppingCartList.value
        val deletedItem = list?.removeAt(position)
        shoppingCartList.value = list
        GlobalScope.launch(Dispatchers.IO) {
            deletedItem?.let { database.itemDao().deleteItem(it) }
        }
    }

    fun searchShoppingCart(inputString: String) {
        if (inputString.isNotBlank()) {
            val filterlist =
                allCartList.filter { item -> isStringPartiallyEqual(item.name, inputString) }
            if (filterlist.isNotEmpty()) {
                shoppingCartList.value = filterlist.toMutableList()
            } else {
                shoppingCartList.value = mutableListOf()
            }
        } else {
            shoppingCartList.value = allCartList
        }
    }

    private fun isStringPartiallyEqual(name: String, inputString: String): Boolean {
        return name.toLowerCase(Locale.ROOT)
            .substring(0, inputString.length) == inputString.toLowerCase(
            Locale.ROOT
        )

    }

    private val callback = object : Callback<Array<Item>?> {
        override fun onResponse(call: Call<Array<Item>?>, response: Response<Array<Item>?>) {
            getPopularItemsOK(response.body())
        }

        override fun onFailure(call: Call<Array<Item>?>, t: Throwable) {
            getPopularItemsERROR(t)
        }
    }

    private fun getPopularItemsOK(items: Array<Item>?) {
        val availableItemsName =
            items?.filter { item -> item.available == Item.AVAILABLE }?.map { item -> item.name }
        popularItems.value = availableItemsName
    }

    private fun getPopularItemsERROR(error: Throwable) {
        Log.d(TAG, "getPopularItemsERROR: ${error.localizedMessage}")
        Toast.makeText(application, PopularItemsEndpoints.POPULAR_ITEMS, Toast.LENGTH_LONG).show()
    }

    private fun addNewItem(name: String, amount: Int) {
        val newItem = Item(name = name, amount = amount)
        allCartList.add(newItem)
        GlobalScope.launch(Dispatchers.IO) {
            val newIds = database.itemDao().insertAllItems(newItem)
            allCartList.last().id = newIds[0]
        }
    }

    private fun updateItem(index: Int, amount: Int) {
        allCartList[index].amount = allCartList[index].amount + amount
        GlobalScope.launch(Dispatchers.IO) {
            database.itemDao().updateItem(allCartList[index])
        }
    }
}