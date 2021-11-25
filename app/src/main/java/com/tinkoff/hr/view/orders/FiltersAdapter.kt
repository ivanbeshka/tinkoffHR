package com.tinkoff.hr.view.orders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.databinding.ItemOrdersFilterBinding

class FiltersAdapter(private val listener: OnFilterClickListener) : RecyclerView.Adapter<FiltersAdapter.ViewHolder>() {
    private var data: List<Filter> = listOf()

    class ViewHolder(val binding: ItemOrdersFilterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemOrdersFilterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = data[position]

        holder.binding.filter = filter
        val isSelected = filter.isSelected

        holder.binding.chipFilter.setOnClickListener {
            listener.onFilterClick(!isSelected, position)
        }
    }

    fun setData(filters: List<Filter>) {
        if (data.isEmpty()) {
            data = filters
            notifyDataSetChanged()
        } else {
            val callback = FiltersDiffUtilCallback(data, filters)
            val util = DiffUtil.calculateDiff(callback)
            data = filters
            util.dispatchUpdatesTo(this)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnFilterClickListener {
        fun onFilterClick(isSelected: Boolean, position: Int)
    }

    class FiltersDiffUtilCallback(
        private val oldFilters: List<Filter>,
        private val newFilters: List<Filter>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldFilters.size
        }

        override fun getNewListSize(): Int {
            return newFilters.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldFilters[oldItemPosition].name == newFilters[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldFilter = oldFilters[oldItemPosition]
            val newFilter = newFilters[newItemPosition]
            return oldFilter.isSelected == newFilter.isSelected
        }

    }
}