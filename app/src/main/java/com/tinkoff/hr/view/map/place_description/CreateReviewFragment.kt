package com.tinkoff.hr.view.map.place_description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tinkoff.hr.databinding.FragmentCreateReviewBinding
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

        placesViewModel.getPlaceById(placeId).observe(viewLifecycleOwner) { place ->
            place?.let { binding.place = it }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnSendReview.setOnClickListener {
            findNavController().navigateUp()
        }


        return binding.root
    }
}