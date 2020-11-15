package com.agusmagne.shoplisttestapp.ui.main.popularitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.agusmagne.shoplisttestapp.R
import com.agusmagne.shoplisttestapp.vm.ShopListViewModel
import com.agusmagne.shoplisttestapp.vm.ViewModelFactoryProviders
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.fragment_popular_item_dialog.*
import javax.inject.Inject

class PopularItemDialogFragment : DaggerDialogFragment() {

    @Inject
    lateinit var providersFactory: ViewModelFactoryProviders
    lateinit var viewmodel: ShopListViewModel

    companion object {
        const val NAME = "NAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel = ViewModelProvider(
            requireActivity(),
            providersFactory
        ).get(ShopListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_popular_item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildNumberPicker()
        addItemBtn.setOnClickListener(addItemBtnClickListener)
        name?.text = arguments?.getString(NAME)
    }

    private fun buildNumberPicker() {
        numberpicker.apply {
            maxValue = 100
            minValue = 1
            wrapSelectorWheel = false
        }
    }

    private val addItemBtnClickListener = View.OnClickListener {
        viewmodel.addItem(name.text.toString(), numberpicker.value)
        dialog?.dismiss()
    }


}