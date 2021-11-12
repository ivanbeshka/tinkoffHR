package com.tinkoff.hr.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinkoff.hr.data.Order
import com.tinkoff.hr.databinding.FragmentOrdersBinding
import com.tinkoff.hr.viewmodels.FiltersViewModel
import com.tinkoff.hr.viewmodels.OrdersViewModel

class OrdersFragment : Fragment(), OrdersAdapter.OnItemClickListener, FiltersAdapter.OnFilterClickListener {

    private val filtersViewModel: FiltersViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)

        binding.rvOrders.layoutManager = GridLayoutManager(requireContext(), 2)

        ordersViewModel.getOrders().observe(viewLifecycleOwner){
            val ordersAdapter = OrdersAdapter(it, this)
            binding.rvOrders.adapter = ordersAdapter
        }


        binding.rvFilters.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        filtersViewModel.getFilters().observe(viewLifecycleOwner){
            val filtersAdapter = FiltersAdapter(it, this)
            binding.rvFilters.adapter = filtersAdapter
        }

        return binding.root
    }

    override fun onFilterClick(isChecked: Boolean, position: Int) {
        filtersViewModel.setFilterIsChecked(isChecked, position)
    }

    override fun onItemClick(position: Int, isSelected: Boolean) {
        ordersViewModel.setIsSelected(position, isSelected)
    }
}