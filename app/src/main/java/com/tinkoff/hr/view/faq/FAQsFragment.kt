package com.tinkoff.hr.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tinkoff.hr.data.FAQ
import com.tinkoff.hr.databinding.FragmentFaqsBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.FAQViewModel

class FAQsFragment : Fragment(), FAQsAdapter.OnClickListener {

    private val faqViewModel: FAQViewModel by activityViewModels()

    private val faqAdapter = FAQsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFaqsBinding.inflate(inflater, container, false)

        binding.rvFaq.adapter = faqAdapter

        faqViewModel.getFAQs().observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    faqAdapter.data = it
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )
        }

        return binding.root
    }

    override fun onClick(faq: FAQ) {
        findNavController().navigate(FAQsFragmentDirections.actionFaqToFAQDesc())
        faqViewModel.setCurrentFAQ(faq)
    }
}