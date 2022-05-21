package com.tinkoff.hr.view.map.place_description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.domain.PlaceReview
import com.tinkoff.hr.databinding.ItemPlaceReviewBinding

class ReviewsAdapter(private val data: List<PlaceReview>) : RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPlaceReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceReviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = data[position]
        holder.binding.review = review
    }

    override fun getItemCount(): Int {
        return data.size
    }
}