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

    private companion object {
        const val SPAN_COUNT = 2
    }

    private val filtersViewModel: FiltersViewModel by viewModels()
    private val ordersViewModel: OrdersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOrdersBinding.inflate(inflater, container, false)

        binding.rvOrders.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        binding.rvOrders.adapter = OrdersAdapter(this)

        ordersViewModel.getOrders().observe(viewLifecycleOwner){
            (binding.rvOrders.adapter as OrdersAdapter).setData(it)
        }

        binding.rvFilters.adapter = FiltersAdapter(this)
        filtersViewModel.getFilters().observe(viewLifecycleOwner){
            (binding.rvFilters.adapter as FiltersAdapter).setData(it)
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