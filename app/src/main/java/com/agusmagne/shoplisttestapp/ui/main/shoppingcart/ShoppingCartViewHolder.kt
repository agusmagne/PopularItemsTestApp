package com.agusmagne.shoplisttestapp.ui.main.shoppingcart

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.agusmagne.shoplisttestapp.model.entities.Item
import kotlinx.android.synthetic.main.shoppingcart_single.view.*

class ShoppingCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(item: Item) {
        val itemName = item.name
        val itemAmount = item.amount
        itemView.name.text = itemName
        itemView.total.text = buildAmount(itemAmount)
    }

    private fun buildAmount(amount: Int): String = "Total: $amount"
}