package com.tinkoff.hr.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnStartWork.setOnClickListener {
            val email = binding.etEmail.text.toString()
            if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                findNavController().navigate(R.id.registerCodeFragment)
            } else {
                Toast.makeText(context, "Неправильный E-mail!", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}