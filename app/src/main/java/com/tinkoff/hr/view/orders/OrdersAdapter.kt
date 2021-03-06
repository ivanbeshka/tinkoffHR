package com.tinkoff.hr.view.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.tinkoff.hr.R
import com.tinkoff.hr.domain.Product
import com.tinkoff.hr.databinding.ItemOrderBinding

class OrdersAdapter(
    private val listener: OnProductClickListener
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, OrderDiffUtilCallback())
    var data: List<Product>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    class ViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            ItemOrderBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = data[position]
        holder.binding.order = order

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
            listener.onItemClick(order.id, isSelected)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class OrderDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    private fun setRightCardBackground(v: MaterialCardView, selected: Boolean) {
        if (!selected) {
            v.elevation = ELEVATION_NORMAL
            v.setCardBackgroundColor(
                ContextCompat.getColor(
                    v.context,
                    R.color.white
                )
            )
        } else {
            v.elevation = ELEVATION_PRESSED
            v.setCardBackgroundColor(
                ContextCompat.getColor(
                    v.context,
                    R.color.pressed_view
                )
            )
        }
    }

    interface OnProductClickListener {
        fun onItemClick(productId: String, isSelected: Boolean)
    }

    private companion object {
        const val ELEVATION_PRESSED = 7f
        const val ELEVATION_NORMAL = 10f
    }
}