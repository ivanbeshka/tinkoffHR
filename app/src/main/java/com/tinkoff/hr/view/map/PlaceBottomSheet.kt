package com.tinkoff.hr.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tinkoff.hr.data.Place
import com.tinkoff.hr.databinding.BottomSheetPlaceBinding

class PlaceBottomSheet(private val place: Place) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BottomSheetPlaceBinding.inflate(inflater, container, false)
        binding.place = place

        binding.rvReviews.adapter = ReviewsAdapter(place.reviews)

        return binding.root
    }

    companion object {
        const val TAG = "BottomSheetPlace"
    }
}