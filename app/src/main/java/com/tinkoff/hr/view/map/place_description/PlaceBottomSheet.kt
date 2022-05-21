package com.tinkoff.hr.view.map.place_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.databinding.BottomSheetPlaceBinding
import com.tinkoff.hr.view.map.MapFragmentDirections

class PlaceBottomSheet(private val place: Place) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BottomSheetPlaceBinding.inflate(inflater, container, false)
        binding.place = place

        binding.rvReviews.adapter = ReviewsAdapter(place.reviews)

        binding.btnCreateReview.setOnClickListener {
            findNavController().navigate(
                MapFragmentDirections.actionFragmentMapToCreateReviewFragment(place.id)
            )
        }

        return binding.root
    }

    companion object {
        const val TAG = "BottomSheetPlace"
    }
}