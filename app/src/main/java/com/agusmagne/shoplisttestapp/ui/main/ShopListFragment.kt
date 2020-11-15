package com.agusmagne.shoplisttestapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.agusmagne.shoplisttestapp.R
import com.agusmagne.shoplisttestapp.model.entities.Item
import com.agusmagne.shoplisttestapp.ui.main.popularitems.PopularItemDialogFragment
import com.agusmagne.shoplisttestapp.ui.main.shoppingcart.ShoppingCartAdapter
import com.agusmagne.shoplisttestapp.ui.main.shoppingcart.ShoppingCartDialogFragment
import com.agusmagne.shoplisttestapp.vm.ShopListViewModel
import com.agusmagne.shoplisttestapp.vm.ViewModelFactoryProviders
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_popularitems.*
import kotlinx.android.synthetic.main.fragment_shoppingcart.*
import javax.inject.Inject

class ShopListFragment : DaggerFragment() {

    private val TAG = "ShopListFragment"

    @Inject
    lateinit var providersFactory: ViewModelFactoryProviders

    private lateinit var viewmodel: ShopListViewModel
    private lateinit var popularItemsAdapter: ArrayAdapter<String>
    private lateinit var shoppingCartAdapter: ShoppingCartAdapter
    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = requireArguments().getInt(ARG_SECTION_NUMBER)
        viewmodel = ViewModelProvider(
            requireActivity(),
            providersFactory
        ).get(ShopListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (index) {
            1 -> {
                inflater.inflate(R.layout.fragment_popularitems, container, false)
            }
            2 -> {
                inflater.inflate(R.layout.fragment_shoppingcart, container, false)
            }
            else -> {
                inflater.inflate(0, container, false)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePage(index)
    }

    @Suppress("UNCHECKED_CAST")
    private val popularItemsObserver = Observer<List<String>> {
        showProgressbar(false)
        popularItemsAdapter.apply { clear(); addAll(it) }
    }

    private fun initializePage(index: Int?) {
        if (index == 1) {
            buildPopularItemsList()
            setPopularItemsListClickListener()
        }
        if (index == 2) {
            buildShoppingCartList()
            setSearchbarOnActionListener()
        }
    }

    private fun setPopularItemsListClickListener() {
        popularitems_list.setOnItemClickListener { parent, textview, position, id ->
            val text = (textview as TextView).text.toString()
            val dialog =
                PopularItemDialogFragment()
            val bundle = Bundle()
            bundle.putString(PopularItemDialogFragment.NAME, text)
            dialog.arguments = bundle
            dialog.show(requireFragmentManager(), null)
        }
    }

    private fun setSearchbarOnActionListener() {
        searchEdtxt.setOnEditorActionListener { v, actionId, event ->
            viewmodel.searchShoppingCart(v.text.toString().trim())
            true
        }
    }

    private fun buildPopularItemsList() {
        showProgressbar(true)
        createAdapterAndFetchPopularItems()
    }

    private fun createAdapterAndFetchPopularItems() {
        popularItemsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
        popularitems_list.adapter = popularItemsAdapter
        viewmodel.popularItems.observe(this, popularItemsObserver)
        viewmodel.fetchPopularItems()
    }

    private fun buildShoppingCartList() {
        shoppingCartAdapter =
            ShoppingCartAdapter(
                mutableListOf()
            ) { position ->
                viewmodel.deleteItem(position)
            }
        shoppingcart_rv.apply {
            adapter = shoppingCartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewmodel.shoppingCartList.observe(this, shoppingCartListObserver)
    }

    private val shoppingCartListObserver = Observer<List<Item>> {
        shoppingCartAdapter.updateList(it)
        shoppingCartAdapter.notifyDataSetChanged()
    }

    private fun showProgressbar(show: Boolean) {
        if (show) {
            progress_popularitems.visibility = View.VISIBLE
        } else {
            progress_popularitems.visibility = View.INVISIBLE
        }
    }

    fun openNewItemDialogFragment() {
        ShoppingCartDialogFragment().show(requireFragmentManager(), null)
    }

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ShopListFragment {
            return ShopListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}