package com.tinkoff.hr.view.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.FAQ
import com.tinkoff.hr.databinding.ItemFaqBinding
import net.cachapa.expandablelayout.ExpandableLayout

class FAQAdapter(private val data: List<FAQ>) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFaqBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: FAQ){
            binding.faq = faq
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFaqBinding = DataBindingUtil.inflate(inflater, R.layout.item_faq, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = data[position]

        holder.bind(faq)

        holder.binding.btnOpenCloseDesc.setOnClickListener {
            val isExpanded = holder.binding.layoutExpandable.isExpanded

            if (isExpanded) {
                holder.binding.layoutExpandable.collapse(true)
                holder.binding.btnOpenCloseDesc.setImageResource(R.drawable.ic_arrow_down)
            } else {
                holder.binding.layoutExpandable.expand(true)
                holder.binding.btnOpenCloseDesc.setImageResource(R.drawable.ic_arrow_up)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}