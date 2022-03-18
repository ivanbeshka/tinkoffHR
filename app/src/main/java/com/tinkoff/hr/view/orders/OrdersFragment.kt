package com.tinkoff.hr.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tinkoff.hr.databinding.FragmentOrdersBinding
import com.tinkoff.hr.viewmodels.FiltersViewModel
import com.tinkoff.hr.viewmodels.OrdersViewModel

class OrdersFragment : Fragment(), OrdersAdapter.OnOrderClickListener, FiltersAdapter.OnFilterClickListener {

    private val filtersViewModel: FiltersViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()

    private val ordersAdapter = OrdersAdapter(this)
    private val filtersAdapter= FiltersAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)

        binding.rvOrders.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        binding.rvOrders.adapter = ordersAdapter
        binding.rvOrders.itemAnimator = null
        ordersViewModel.getOrders().observe(viewLifecycleOwner){
            ordersAdapter.data = it
        }

        binding.rvFilters.adapter = filtersAdapter
        binding.rvFilters.itemAnimator = null
        filtersViewModel.getFilters().observe(viewLifecycleOwner){
            filtersAdapter.data = it
            ordersViewModel.setFilters(it)
        }

        return binding.root
    }

    override fun onFilterClick(isSelected: Boolean, position: Int) {
        filtersViewModel.setFilterIsSelected(isSelected, position)
    }

    override fun onItemClick(orderId: Int, isSelected: Boolean) {
        ordersViewModel.setIsSelected(orderId, isSelected)
    }

    private companion object {
        const val SPAN_COUNT = 2
    }
}