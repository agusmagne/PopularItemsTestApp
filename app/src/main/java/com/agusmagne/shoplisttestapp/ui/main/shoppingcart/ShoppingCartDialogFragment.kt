package com.agusmagne.shoplisttestapp.ui.main.shoppingcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.agusmagne.shoplisttestapp.R
import com.agusmagne.shoplisttestapp.vm.ShopListViewModel
import com.agusmagne.shoplisttestapp.vm.ViewModelFactoryProviders
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_shopping_cart_dialog.*
import javax.inject.Inject


class ShoppingCartDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var factoryProviders: ViewModelFactoryProviders
    lateinit var viewmodel: ShopListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(
            requireActivity(),
            factoryProviders
        ).get(ShopListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_shopping_cart_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildNumberPicker()
        setAddNewItemListener()
    }

    private fun buildNumberPicker() {
        addNewItemNumberPicker.apply {
            maxValue = 100
            minValue = 1
            wrapSelectorWheel = false
        }
    }

    private fun setAddNewItemListener() {
        addNewItemBtn.setOnClickListener {
            addItem()
            dialog?.dismiss()
        }
    }

    private fun addItem() {
        val name = newItemEdtxt.text.toString().trim()
        val amount = addNewItemNumberPicker.value
        if (name.isNotBlank()) {
            viewmodel.addItem(name, amount)
        } else {
            Toast.makeText(
                requireContext(),
                "Each item must have a name. Please enter one",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}