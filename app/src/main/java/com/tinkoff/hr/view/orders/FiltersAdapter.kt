package com.tinkoff.hr.view.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.databinding.ItemOrdersFilterBinding

class FiltersAdapter(private val listener: OnFilterClickListener) : RecyclerView.Adapter<FiltersAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, FiltersDiffUtilCallback())
    var data: List<Filter>
        get() = differ.currentList
        set(value) = differ.submitList(value)

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

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnFilterClickListener {
        fun onFilterClick(isSelected: Boolean, position: Int)
    }

    class FiltersDiffUtilCallback : DiffUtil.ItemCallback<Filter>() {
        override fun areItemsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Filter, newItem: Filter): Boolean {
            return oldItem == newItem
        }
    }
}