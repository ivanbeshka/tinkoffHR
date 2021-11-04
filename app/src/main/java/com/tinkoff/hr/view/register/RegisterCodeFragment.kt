package com.tinkoff.hr.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chaos.view.PinView
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentRegisterCodeBinding
import com.tinkoff.hr.viewmodels.RegisterViewModel

class RegisterCodeFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterCodeBinding.inflate(inflater, container, false)

        binding.etCode.addTextChangedListener {
            val code = it.toString()
            if (code.length == 4){
                registerViewModel.setCode(code)
            }
        }

        return binding.root
    }

}