package com.tinkoff.hr.view.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.FAQ
import net.cachapa.expandablelayout.ExpandableLayout

class FAQAdapter(private val data: List<FAQ>) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvContent: TextView = view.findViewById(R.id.tv_content)
        val btnOpenCloseDesc: AppCompatImageButton = view.findViewById(R.id.btn_open_close_desc)
        val expandableLayout: ExpandableLayout = view.findViewById(R.id.layout_expandable)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_faq, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faq = data[position]

        holder.tvTitle.text = faq.title
        holder.tvContent.text = faq.content

        holder.btnOpenCloseDesc.setOnClickListener {
            val isExpanded = holder.expandableLayout.isExpanded

            if (isExpanded) {
                holder.expandableLayout.collapse(true)
                holder.btnOpenCloseDesc.setImageResource(R.drawable.ic_arrow_down)
            } else {
                holder.expandableLayout.expand(true)
                holder.btnOpenCloseDesc.setImageResource(R.drawable.ic_arrow_up)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}