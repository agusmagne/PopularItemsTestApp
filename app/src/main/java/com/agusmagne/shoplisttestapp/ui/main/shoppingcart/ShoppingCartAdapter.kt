package com.agusmagne.shoplisttestapp.ui.main.shoppingcart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agusmagne.shoplisttestapp.R
import com.agusmagne.shoplisttestapp.model.entities.Item
import kotlinx.android.synthetic.main.shoppingcart_single.view.*

class ShoppingCartAdapter(
    private var list: MutableList<Item>,
    val onDeleteClickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ShoppingCartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.shoppingcart_single, parent, false)
        return ShoppingCartViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bindView(list[position])
        holder.itemView.delete.setOnClickListener { onDeleteClickListener(position) }
    }

    fun updateList(newList: List<Item>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}