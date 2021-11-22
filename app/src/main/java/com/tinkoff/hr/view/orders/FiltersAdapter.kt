package com.tinkoff.hr.view.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
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


        holder.binding.chipFilter.setOnCheckedChangeListener { chip, isChecked ->
            listener.onFilterClick(isChecked, position)
        }
    }


    fun setData(filters: List<Filter>){
        data = filters
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnFilterClickListener{
        fun onFilterClick(isChecked: Boolean, position: Int)
    }
}