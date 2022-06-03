package com.tinkoff.hr.view.map.place_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentCreateReviewBinding
import com.tinkoff.hr.domain.PlaceReview
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.PlacesViewModel

class CreateReviewFragment : Fragment() {

    private val placesViewModel: PlacesViewModel by viewModels()

    private val navArgs: CreateReviewFragmentArgs by navArgs()
    private val placeId by lazy { navArgs.placeId }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCreateReviewBinding.inflate(inflater, container, false)

        placesViewModel.getPlaceById(placeId).observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    binding.place = it
                },
                error = {
                    showToast(getString(R.string.oops_something_went_wrong))
                }
            )
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSendReview.setOnClickListener {
            with(binding) {
                val pluses = etPluses.text.toString()
                val minuses = etMinuses.text.toString()
                val grade = viewRating.rating
                val price = etPrice.text.toString().toIntOrNull()

                if (!showToastIfBlank(pluses, minuses) && !showToastIfNull(grade, price)) {
                    val placeReview = PlaceReview(
                        "IWPiQDppRkcpJ3n2OUl8",
                        price,
                        grade,
                        pluses,
                        minuses
                    )
                    subscribeOnAddReview(placeReview)
                }
            }
        }

        return binding.root
    }

    private fun subscribeOnAddReview(placeReview: PlaceReview) {
        placesViewModel.addReview(placeReview, placeId).observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    findNavController().navigateUp()
                },
                error = {
                    showToast(getString(R.string.oops_something_went_wrong))
                }
            )
        }
    }

    private fun showToastIfBlank(vararg str: String): Boolean {
        str.forEach {
            if (it.isBlank()) {
                showToast(getString(R.string.fill_in_all_the_fields))
                return true
            }
        }
        return false
    }

    private fun showToastIfNull(vararg int: Int?): Boolean {
        int.forEach {
            if (it == null) {
                showToast(getString(R.string.fill_in_all_the_fields))
                return true
            }
        }
        return false
    }
}