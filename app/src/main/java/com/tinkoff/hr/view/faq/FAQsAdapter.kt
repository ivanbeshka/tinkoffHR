package com.tinkoff.hr.view.faq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.data.FAQ
import com.tinkoff.hr.databinding.ItemFaqBinding

class FAQsAdapter(private val clickListener: OnClickListener) :
    RecyclerView.Adapter<FAQsAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, FAQDiffUtilCallback())
    var data: List<FAQ>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    class ViewHolder(val binding: ItemFaqBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFaqBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = data[position]

        holder.binding.faq = faq
        holder.binding.containerItemFaq.setOnClickListener {
            clickListener.onClick(faq)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class FAQDiffUtilCallback : DiffUtil.ItemCallback<FAQ>() {
        override fun areItemsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: FAQ, newItem: FAQ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnClickListener {
        fun onClick(faq: FAQ)
    }
}