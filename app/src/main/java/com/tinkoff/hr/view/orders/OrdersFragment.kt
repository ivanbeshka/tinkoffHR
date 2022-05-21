package com.tinkoff.hr.view.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.tinkoff.hr.databinding.FragmentOrdersBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.FiltersViewModel
import com.tinkoff.hr.viewmodels.OrdersViewModel

class OrdersFragment : Fragment(), OrdersAdapter.OnProductClickListener, FiltersAdapter.OnFilterClickListener {

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

        ordersViewModel.getOrders().observe(viewLifecycleOwner){ state ->
            state.on(
                success = {
                    ordersAdapter.data = it
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )

        }

        binding.rvFilters.adapter = filtersAdapter
        binding.rvFilters.itemAnimator = null
        filtersViewModel.getFilters().observe(viewLifecycleOwner){ state ->
            state.on(
                success = {
                    filtersAdapter.data = it
                    ordersViewModel.setFilters(it)
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )

        }

        return binding.root
    }

    override fun onFilterClick(isSelected: Boolean, position: Int) {
        filtersViewModel.setFilterIsSelected(isSelected, position)
    }

    override fun onItemClick(productId: String, isSelected: Boolean) {
        ordersViewModel.setIsSelected(productId, isSelected)
    }

    private companion object {
        const val SPAN_COUNT = 2
    }
}