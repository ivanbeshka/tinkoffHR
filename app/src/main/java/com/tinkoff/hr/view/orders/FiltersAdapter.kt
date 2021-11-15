package com.tinkoff.hr.view.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Filter
import com.tinkoff.hr.databinding.ItemOrdersFilterBinding

class FiltersAdapter(private val data: List<Filter>, private val listener: OnFilterClickListener) : RecyclerView.Adapter<FiltersAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemOrdersFilterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: Filter){
            binding.filter = filter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemOrdersFilterBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_orders_filter, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = data[position]
        holder.bind(filter)

        holder.binding.chipFilter.setOnCheckedChangeListener { chip, isChecked ->
            listener.onFilterClick(isChecked, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnFilterClickListener{
        fun onFilterClick(isChecked: Boolean, position: Int)
    }
}