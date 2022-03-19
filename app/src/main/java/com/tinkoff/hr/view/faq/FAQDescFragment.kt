package com.tinkoff.hr.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tinkoff.hr.databinding.FragmentFaqDescBinding
import com.tinkoff.hr.viewmodels.FAQViewModel

class FAQDescFragment : Fragment() {

    private val faqViewModel: FAQViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFaqDescBinding.inflate(inflater, container, false)

        faqViewModel.getCurrentFAQ().observe(viewLifecycleOwner) {
            binding.faq = it
        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}