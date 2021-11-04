package com.tinkoff.hr.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tinkoff.hr.databinding.FragmentRegisterContainerBinding

class FragmentRegisterContainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterContainerBinding.inflate(inflater, container, false)

        return binding.root
    }
}