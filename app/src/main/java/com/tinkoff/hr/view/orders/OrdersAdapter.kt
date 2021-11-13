package com.tinkoff.hr.view.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Order
import com.tinkoff.hr.databinding.ItemOrderBinding

class OrdersAdapter(
    private val data: List<Order>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            binding.order = order
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemOrderBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_order, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = data[position]
        holder.bind(order)

        setRightCardBackground(holder.binding.layoutOrder, order.selected)

        holder.binding.layoutOrder.setOnClickListener {
            val isSelected: Boolean
            if (order.selected) {
                isSelected = false
                setRightCardBackground(holder.binding.layoutOrder, false)
            } else {
                isSelected = true
                setRightCardBackground(holder.binding.layoutOrder, true)
            }
            listener.onItemClick(position, isSelected)
        }
    }

    private fun setRightCardBackground(v: MaterialCardView, selected: Boolean){
        if (!selected) {
            v.elevation = 10f
            v.setCardBackgroundColor(
                ContextCompat.getColor(
                    v.context,
                    R.color.white
                )
            )
        } else {
            v.elevation = 0f
            v.setCardBackgroundColor(
                ContextCompat.getColor(
                    v.context,
                    R.color.gray
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, isSelected: Boolean)
    }
}