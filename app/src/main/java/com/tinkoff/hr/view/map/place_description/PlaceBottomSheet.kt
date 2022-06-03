package com.tinkoff.hr.view.map.place_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tinkoff.hr.R
import com.tinkoff.hr.domain.Place
import com.tinkoff.hr.databinding.BottomSheetPlaceBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.view.map.MapFragmentDirections
import com.tinkoff.hr.viewmodels.PlacesViewModel

class PlaceBottomSheet : BottomSheetDialogFragment() {

    private val placesViewModel: PlacesViewModel by viewModels()

    private val args: PlaceBottomSheetArgs by navArgs()
    private val placeId: String by lazy { args.placeId }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = BottomSheetPlaceBinding.inflate(inflater, container, false)

        placesViewModel.getPlaceById(placeId).observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    binding.place = it
                    binding.rvReviews.adapter = ReviewsAdapter(it.reviews)
                },
                error = {
                    showToast(getString(R.string.oops_something_went_wrong))
                }
            )
        }

        binding.btnCreateReview.setOnClickListener {
            findNavController().navigate(
                PlaceBottomSheetDirections.actionPlaceBottomSheetToCreateReviewFragment(placeId)
            )
        }

        return binding.root
    }

    companion object {
        const val TAG = "BottomSheetPlace"
    }
}